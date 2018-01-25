package com.taotao.order.pojo;

import java.util.List;

import com.taotao.po.TbOrder;
import com.taotao.po.TbOrderItem;
import com.taotao.po.TbOrderShipping;
/**
 * 订单服务pojo
* <p>Title: Order</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-20
 */
public class Order extends TbOrder{

	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
	

}
