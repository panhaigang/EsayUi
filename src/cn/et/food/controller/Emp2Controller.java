package cn.et.food.controller;



import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.food.entity.Emp;
import cn.et.food.entity.Emp2;
import cn.et.food.entity.Result;
import cn.et.food.service.Emp2Service;

@Controller
public class Emp2Controller {
	@Autowired
	Emp2Service emp2;
	
	
	
	//增加
	@ResponseBody
	@RequestMapping(value="saveEmp2",method=RequestMethod.POST)
		public Result saveEmp(Emp2 e){
			Result r=new Result();
			r.setCode(1);
			try {
				emp2.saveEmp(e);
			} catch (Exception e1) {
				r.setCode(0);
				r.setMessage(e1);
			}
			return r;
		}
	
	
	
	//删除
	@ResponseBody
	@RequestMapping(value="deleteEmp2/{empno}",method=RequestMethod.DELETE)
	public Result deleteEmp(@PathVariable String empno){
		String arr[]=empno.split(",");
		Result r=new Result();
		r.setCode(1);
		try {
			/*String a=null;
			a.toString();*/
			for(int i=0;i<arr.length;i++){
				emp2.deleteEmp(Integer.parseInt(arr[i]));
			}
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}
	
	//修改
	@ResponseBody
	@RequestMapping(value="updateEmp2/{empno}",method=RequestMethod.PUT)
	public Result updateEmp(@PathVariable Integer empno,Emp2 e) throws ParseException{
		e.setId(empno);
		Result r=new Result();
		r.setCode(1);
		try {
			emp2.updateEmp(e);
		} catch (Exception e1) {
			r.setCode(0);
			r.setMessage(e1);
		}
		return r;
	}
	
}