package main;

import model.User;
import service.UserRepository;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        List<User> users = userRepository.getAllUsers();
    }
}
