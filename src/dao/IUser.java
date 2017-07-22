package dao;

import entity.User;

public interface IUser {
	public User findByUserName(String name) throws Exception;
}
