package cn.et.food.service;

import cn.et.food.entity.Emp;
import cn.et.food.utils.PageTools;

public interface EmpService {

	//查询
	public abstract PageTools queryEmp1(String ename,Integer page,Integer rows);

	//增加
	public abstract void saveEmp(Emp emp);
	
	//删除
	public abstract void deleteEmp(Integer empno);
	
	//修改
	public abstract void updateEmp(Emp emp);
}