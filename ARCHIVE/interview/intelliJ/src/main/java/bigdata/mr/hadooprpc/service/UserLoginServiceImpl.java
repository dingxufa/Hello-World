package bigdata.mr.hadooprpc.service;

import bigdata.mr.hadooprpc.protocol.IUserLoginService;

public class UserLoginServiceImpl implements IUserLoginService{

	@Override
	public String login(String name, String passwd) {
		return  name + " logged in successfully..." ;
	}

}
