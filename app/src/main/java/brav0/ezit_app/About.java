package brav0.ezit_app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 18-Jul-17.
 */

public class About {
    String name;
    String info;
    int photoId;

    About(String name, String info, int photoId) {
        this.name = name;
        this.info = info;
        this.photoId = photoId;
    }

    private List<About> persons;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    /*private void initializeData() {
        persons = new ArrayList<>();
        persons.add(new About("A", "23 years old", R.drawable.about_us_icon));
        persons.add(new About("B", "25 years old", R.drawable.app_icon));
        persons.add(new About("C", "35 years old", R.drawable.chat_icon));
        persons.add(new About("D", "35 years old", R.drawable.about_us_icon));
    }*/
}