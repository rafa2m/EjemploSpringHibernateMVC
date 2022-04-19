package com.example.springhibernatemvc;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;
	@RequestMapping(value="/create")
	@ResponseBody
	public String create(String name, String message) {
		try {
			User user = new User();
			Random r = new Random();
			int randomId = r.nextInt(Integer.MAX_VALUE);
			user.setId(randomId);
			user.setUserName(name);
			user.setUserMesagge(message);
			userDao.create(user);
			return Messages.getString("UserController.0"); //$NON-NLS-1$
		}catch(Exception ex) {
			return Messages.getString("UserController.1"); //$NON-NLS-1$
		}
	}
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(int id) {
		try {
			User userToDelete = new User();
			userToDelete.setId(id);
			userDao.delete(userToDelete);
		}catch(Exception e) {
			return Messages.getString("UserController.2"); //$NON-NLS-1$
		}
		return Messages.getString("UserController.3") + id + Messages.getString("UserController.4"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	@RequestMapping(value="/update")
	@ResponseBody
	public String update(int id, String name, String message) {
		try {
			User userToUpdate = userDao.getById(id);
			userToUpdate.setUserName(name);
			userToUpdate.setUserMesagge(message);
			userDao.update(userToUpdate);
		}catch(Exception e) {
			return "No ha sido posible actualizar los datos del usuario";
		}
		return "Datos de usuario actualizados!";
	}
}
