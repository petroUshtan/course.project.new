package interfaces;

import objects.User;

/**
 * Created by Work on 18.04.2017.
 */
public interface AdminForm {

    //Додати нового користувача
    void add(User user);

    //Видалити користувача
    void delete(User user);

    //Змінити параметри користувача
    void edit(User user);

}
