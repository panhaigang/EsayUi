package cn.et.food.service;

import java.util.List;
import java.util.Map;

import cn.et.food.entity.Dept1;
import cn.et.food.entity.TreeNode;
import cn.et.food.utils.PageTools;

public interface Dept1Service {
	public List<TreeNode> queryTreeNode(Integer id);
	
	public PageTools queryEmp2(Integer id,String ename,Integer page,Integer rows);
	
	public List<Map> queryDept1();
	
	
}