package brav0.ezit_app;

import android.widget.Filter;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 06-Jul-17.
 */

public class FilterHelper extends Filter {
    static List<user_dis> currentList;
    static RecyclerAdapter adapter;

    public static FilterHelper newInstance(List<user_dis> currentList, RecyclerAdapter adapter){

        FilterHelper.adapter=adapter;
        FilterHelper.currentList=currentList;
        return new FilterHelper();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if (constraint!=null && constraint.length()>0){
            constraint=constraint.toString().toUpperCase();
            List<user_dis> foundFilters = new ArrayList<>();
            String city;

            for (int i=0;i<currentList.size();i++){
                city=currentList.get(i).getCity();
                if (city.toUpperCase().contains(constraint)){
                    foundFilters.add(currentList.get(i));
                }
            }

            filterResults.count=foundFilters.size();
            filterResults.values=foundFilters;

        }else{
            filterResults.count=currentList.size();
            filterResults.values=currentList;
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setdata((List<user_dis>)results.values);
        adapter.notifyDataSetChanged();
    }
}
