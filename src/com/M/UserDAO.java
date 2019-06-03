package com.M;
import java.util.ArrayList;

import com.C.*;

public interface UserDAO {
	public int AddUser(User user);
	public boolean UpdateUser(User user);
	public boolean LoginUser(User user);
	public ArrayList<User> getUser(User user);
}
