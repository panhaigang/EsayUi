package cn.et.food.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.Dept1Mapper;
import cn.et.food.dao.Emp2Mapper;
import cn.et.food.entity.Dept1;
import cn.et.food.entity.Dept1Example;
import cn.et.food.entity.Emp2;
import cn.et.food.entity.Emp2Example;
import cn.et.food.entity.TreeNode;
import cn.et.food.service.Dept1Service;
import cn.et.food.utils.PageTools;

@Service
public class Dept1ServiceImpl implements Dept1Service {
	@Autowired
	Dept1Mapper dept;
	
	@Autowired
	Emp2Mapper emp;
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.EmpService#queryEmp(java.lang.String)
	 */
	
	
	public List<TreeNode> queryTreeNode(Integer id){
		Dept1Example de=new Dept1Example();
		de.createCriteria().andPidEqualTo(id);
		List<Dept1> deptlist=dept.selectByExample(de);
		List<TreeNode> treelist=new ArrayList<TreeNode>();
		for(Dept1 d:deptlist){
			TreeNode tn=new TreeNode();
			tn.setId(d.getId());
			tn.setText(d.getDname());
			//判断此节点下是否还有子节点
			if(queryTreeNode(d.getId()).size()==0){
				tn.setState("open");	
			}
			treelist.add(tn);
		}
		return treelist;
	}
	
	public PageTools queryEmp2(Integer id,String ename,Integer page,Integer rows){
		
		Emp2Example ee=new Emp2Example();
		if(id!=null){
			ee.createCriteria().andDeptidEqualTo(id);
		}
		//查询总记录
		int total=queryEmpCount(ee);
		
		
		String name1 = null;
		if(ename==null||ename==""){
			
		}else{
			try {
				name1 = URLDecoder.decode(ename, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(name1==null){
			name1="";
		}
		
		ee.createCriteria().andEnameLike("%"+name1+"%");
		
		
		
		
		/**
		 * page   当前页
		 * queryEmpCount(ename)  总记录数
		 * rows   每页显示的条数
		 */
		PageTools pts=new PageTools(page,total,rows);
		//传入开始位置 和 每行显示的条数
		RowBounds rb=new RowBounds(pts.getStartIndex()-1, rows);
		//需要传入一个example和rowBounds对象
		List<Emp2> list=emp.selectByExampleWithRowbounds(ee, rb);
		
		pts.setRows(list);
		return pts;
	}
	
	//查询数据库的总记录数
	public int queryEmpCount(Emp2Example ee){
		return emp.countByExample(ee);
	}
	
	
	
	public List<Map> queryDept1(){
		List<Map> l=new ArrayList<Map>();
		Dept1Example de=new Dept1Example();
		List<Dept1> list=dept.selectByExample(de);
		for(Dept1 d:list){
			Map map=new HashMap();
			map.put("id", d.getId());
			map.put("text", d.getDname());
			l.add(map);
		}
		return l;
	}
}