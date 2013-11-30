package be.hp.workshop.util;

import java.util.LinkedList;
import java.util.List;

import be.hp.workshop.R;

/**
 * Created by bart on 11/30/13.
 */
public class HardcodedImages {

    // some hardcoded images from http://dryicons.com/free-icons/preview/colorful-stickers-part-5-icons-set/
    public static List<Integer> getHardcodedImageList() {
        List<Integer> imageList = new LinkedList<Integer>();

        imageList.add(0, R.drawable.box);
        imageList.add(1, R.drawable.empty_calendar);
        imageList.add(2, R.drawable.full_screen);
        imageList.add(3, R.drawable.megaphone);
        imageList.add(4, R.drawable.wired);

        return imageList;
    }
}
