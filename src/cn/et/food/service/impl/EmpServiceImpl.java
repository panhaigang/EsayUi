package cn.et.food.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.EmpMapper;
import cn.et.food.entity.Emp;
import cn.et.food.entity.EmpExample;
import cn.et.food.service.EmpService;
import cn.et.food.utils.PageTools;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	EmpMapper emp;
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.EmpService#queryEmp(java.lang.String)
	 */
	public PageTools queryEmp1(String ename,Integer page,Integer rows){
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
		
		EmpExample ee=new EmpExample();
		ee.createCriteria().andEnameLike("%"+name1+"%");
		int total=queryEmpCount(ee);
		
		/**
		 * page   当前页
		 * queryEmpCount(ename)  总记录数
		 * rows   每页显示的条数
		 */
		PageTools pts=new PageTools(page,total,rows);
		//传入开始位置 和 每行显示的条数
		RowBounds rb=new RowBounds(pts.getStartIndex()-1, rows);
		//需要传入一个example和rowBounds对象
		List<Emp> list=emp.selectByExampleWithRowbounds(ee, rb);
		
		
		pts.setRows(list);
		return pts;
	}
	
	
	//查询数据库的总记录数
	public int queryEmpCount(EmpExample ee){
		return emp.countByExample(ee);
		
	}
	
	
	public void saveEmp(Emp s) {
		emp.insertSelective(s);
	}


	public void deleteEmp(Integer empno) {
		emp.deleteByPrimaryKey(empno);
	}


	public void updateEmp(Emp s) {
		emp.updateByPrimaryKey(s);
		
	}
}