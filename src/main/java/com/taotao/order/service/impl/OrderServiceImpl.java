package com.taotao.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.service.OrderService;
import com.taotao.po.TbOrder;
import com.taotao.po.TbOrderItem;
import com.taotao.po.TbOrderShipping;
@Service
public class OrderServiceImpl implements OrderService{
	//#订单在redis中 生成id的key
	@Value("${ORDER_KEY_REDIS}")
	private String ORDER_KEY_REDIS;
	//#单在redis中 生成id的key初始值
	@Value("${ORDER_KEY_VSLUE}")
	private String ORDER_KEY_VSLUE;
	//#订单明细id 无初始化值
	@Value("${ORDER_DETAIL_KEY}")
	private String ORDER_DETAIL_KEY;
	
	@Autowired 
	private TbOrderMapper orderMapper;
	
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;
	
	@Autowired
	private JedisClient jedisClient;

	@Override
	public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList,
			TbOrderShipping orderShipping) {
		////获得订单号
		String string = jedisClient.get(ORDER_KEY_REDIS);
		if(StringUtils.isBlank(string)){
			jedisClient.set(ORDER_KEY_REDIS, ORDER_KEY_VSLUE);
		}
		long orderId = jedisClient.incr(ORDER_KEY_REDIS);
		
		Date date=new Date();
		//补全order
		order.setOrderId(orderId+"");
		order.setCreateTime(date);
		order.setUpdateTime(date);
		//状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		order.setStatus(1);
		//0：未评价 1：已评价
		order.setBuyerRate(0);
		//向订单表插入数据
		orderMapper.insert(order);
		
		//插入订单明细
		for (TbOrderItem tbOrderItem : itemList) {
			//获得明细id
			long orderItemId = jedisClient.incr(ORDER_DETAIL_KEY);
			tbOrderItem.setId(orderItemId+"");
			
			//向订单明细插入记录
			orderItemMapper.insert(tbOrderItem);

		}
		
		//插入物流表
		//补全物流表的属性
		orderShipping.setOrderId(orderId + "");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		
		return TaotaoResult.ok(orderId);
	}

}
