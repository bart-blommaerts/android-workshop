package be.hp.workshop;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import be.hp.workshop.data.model.BrownBag;
import be.hp.workshop.data.model.BrownBagItems;
import be.hp.workshop.data.service.BrownBagService;

/**
 * A list fragment representing a list of Items. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link ItemDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ItemListFragment extends ListFragment {

    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    private Callbacks mCallbacks = sDummyCallbacks;
    private int mActivatedPosition = ListView.INVALID_POSITION;

    private BrownBagService brownBagService = new BrownBagService();
    private SimpleAdapter adapter;
    private List<Map<String,String>> brownBagListWithImages;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    public ItemListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        brownBagListWithImages = getBrownBagListWithImages();

        // Keys used in Hashmap
        String[] from = { "image","title" };

        // Ids of views in listview_layout
        int[] to = { R.id.brown_bag_image,R.id.brown_bag_title};

        adapter = new SimpleAdapter(getActivity().getBaseContext(), brownBagListWithImages, R.layout.listview_layout, from, to);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        brownBagListWithImages.clear();
        brownBagListWithImages.addAll(getBrownBagListWithImages());

        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(BrownBagItems.ITEMS.get(position).getId());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    private List<Map<String,String>> getBrownBagListWithImages() {
        getBrownBagContent();

        List<Map<String,String>> aList = new ArrayList<Map<String, String>>();

        List<Integer> imageList = getHardcodedImageList();

        for (BrownBag brownBag : BrownBagItems.ITEMS) {
            Map<String, String> hm = new LinkedHashMap<String, String>();
            hm.put("image", Integer.toString(imageList.get(brownBag.getImageId())));
            hm.put("title", brownBag.getTitle());
            aList.add(hm);
        }

        return aList;
    }

    private void getBrownBagContent() {
        brownBagService.findAll(getActivity().getBaseContext());
    }

    // some hardcoded images from http://dryicons.com/free-icons/preview/colorful-stickers-part-5-icons-set/
    private List<Integer> getHardcodedImageList() {
        List<Integer> imageList = new LinkedList<Integer>();

        imageList.add(0, R.drawable.box);
        imageList.add(1, R.drawable.empty_calendar);
        imageList.add(2, R.drawable.full_screen);
        imageList.add(3, R.drawable.megaphone);
        imageList.add(4, R.drawable.wired);

        return imageList;
    }
}
