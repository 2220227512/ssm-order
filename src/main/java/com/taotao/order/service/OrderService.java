package com.taotao.order.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.po.TbOrder;
import com.taotao.po.TbOrderItem;
import com.taotao.po.TbOrderShipping;

/**
 * 订单服务Service
* <p>Title: OrderService</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-20
 */
public interface OrderService {
	/**
	 * 创建订单
	 * <p>Title: createOrder</p>  
	 * <p>Description: </p>  
	 * @param order
	 * @param itemList
	 * @param orderShipping
	 * @return
	 */
	TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);

}
