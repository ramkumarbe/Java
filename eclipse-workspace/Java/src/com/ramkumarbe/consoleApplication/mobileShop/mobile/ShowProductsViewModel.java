package com.ramkumarbe.consoleApplication.mobileShop.mobile;

import java.util.Map;
import java.util.Stack;

import com.ramkumarbe.consoleApplication.mobileShop.repository.MobileRepository;

public class ShowProductsViewModel {
	Stack<Map> stack;
	ShowProducts showProducts;
	Map<String,Object> data;
	ShowProductsViewModel(ShowProducts showProducts) {
		this.showProducts = showProducts;
		stack = new Stack<Map>();
	}
    public void printData() {
    	Map<String,String> data = stack.peek();
    	for (Map.Entry<String, String> map : data.entrySet()) {
    		showProducts.printValue(map.getKey(), map.getValue());
		}
    }
     
	public void getBack() {
		if(stack.size()>1) {
			stack.pop();
		}
	}
	public boolean isValidInput(int selection) {
		return selection<=stack.peek().size();
	}
	@SuppressWarnings("unchecked")
	public void selectData(int selection) {
		stack.push((Map<String,String>)data.get(stack.peek().get(String.valueOf(selection))));
	}
	@SuppressWarnings("unchecked")
	public void start() {
		this.data = MobileRepository.getInstance().getData();
		stack.push((Map<String, Object>) data.get("mobileBrands"));
	}
}
