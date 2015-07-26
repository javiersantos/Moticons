package com.javiersantos.moticons.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.javiersantos.moticons.Moticon;
import com.javiersantos.moticons.MoticonsApplication;
import com.javiersantos.moticons.R;
import com.javiersantos.moticons.adapters.MoticonAdapter;
import com.javiersantos.moticons.utils.AppPreferences;
import com.javiersantos.moticons.utils.UtilsMoticons;
import com.javiersantos.moticons.utils.UtilsUI;
import com.mikepenz.materialdrawer.Drawer;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    // UI
    private Toolbar toolbar;
    private Context context;
    private RecyclerView recyclerView;
    //private SwipyRefreshLayout swipyRefreshLayout;
    private Drawer drawer;
    private MenuItem searchItem;
    private SearchView searchView;
    private ProgressWheel progressWheel;
    private static VerticalRecyclerViewFastScroller fastScroller;
    private static LinearLayout noResults;
    private static MenuItem moticoinsItem;

    // MainActivity variables
    private AppPreferences appPreferences;

    private List<Moticon> moticonList;
    private List<Moticon> moticonPositiveList;
    private List<Moticon> moticonNegativeList;
    private List<Moticon> moticonFunnyList;
    private List<Moticon> moticonAnimalsList;
    private MoticonAdapter moticonAdapter;
    private MoticonAdapter moticonPositiveAdapter;
    private MoticonAdapter moticonNegativeAdapter;
    private MoticonAdapter moticonFunnyAdapter;
    private MoticonAdapter moticonAnimalsAdapter;
    private Boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;
        this.appPreferences = MoticonsApplication.getAppPreferences();

        initUI();
        isFirstRun();

        recyclerView = (RecyclerView) findViewById(R.id.moticonList);
        //swipyRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.pull_to_refresh);
        fastScroller = (VerticalRecyclerViewFastScroller) findViewById(R.id.fast_scroller);
        progressWheel = (ProgressWheel) findViewById(R.id.progress);
        noResults = (LinearLayout) findViewById(R.id.noResults);

        fastScroller.setRecyclerView(recyclerView);
        recyclerView.setOnScrollListener(fastScroller.getOnScrollListener());
        progressWheel.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //setPullToRefresh(swipyRefreshLayout);
        drawer = UtilsUI.showNavigationDrawer(context, toolbar, moticonAdapter, moticonPositiveAdapter, moticonNegativeAdapter, moticonFunnyAdapter, moticonAnimalsAdapter, recyclerView);

        new getMoticons().execute();

    }

    private void initUI() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.toolbar.setElevation(20);
        }
    }

    private void isFirstRun() {
        if (appPreferences.getFirstRun()) {
            startActivity(new Intent(context, IntroActivity.class));
            finish();
        }
    }

    class getMoticons extends AsyncTask<Void, String, Void> {
        public getMoticons() {
            moticonList = new ArrayList<>();
            moticonPositiveList = new ArrayList<>();
            moticonNegativeList = new ArrayList<>();
            moticonFunnyList = new ArrayList<>();
            moticonAnimalsList = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            moticonList = UtilsMoticons.loadMoticons();
            Collections.sort(moticonList, new Comparator<Moticon>() {
                @Override
                public int compare(Moticon moticon1, Moticon moticon2) {
                    return moticon2.getTimes().compareTo(moticon1.getTimes());
                }
            });

            for (Moticon moticon : moticonList) {
                switch (moticon.getCategory()) {
                    case POSITIVE:
                        moticonPositiveList.add(moticon);
                        break;
                    case NEGATIVE:
                        moticonNegativeList.add(moticon);
                        break;
                    case FUNNY:
                        moticonFunnyList.add(moticon);
                        break;
                    case ANIMAL:
                        moticonAnimalsList.add(moticon);
                        break;
                    default:
                        break;
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            moticonAdapter = new MoticonAdapter(moticonList, context);
            moticonPositiveAdapter = new MoticonAdapter(moticonPositiveList, context);
            moticonNegativeAdapter = new MoticonAdapter(moticonNegativeList, context);
            moticonFunnyAdapter = new MoticonAdapter(moticonFunnyList, context);
            moticonAnimalsAdapter = new MoticonAdapter(moticonAnimalsList, context);

            fastScroller.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(moticonAdapter);
            progressWheel.setVisibility(View.GONE);

            drawer = UtilsUI.showNavigationDrawer(context, toolbar, moticonAdapter, moticonPositiveAdapter, moticonNegativeAdapter, moticonFunnyAdapter, moticonAnimalsAdapter, recyclerView);
        }

    }

    private void setPullToRefresh(final SwipyRefreshLayout swipyRefreshLayout) {
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                switch (direction) {
                    case TOP:
                        moticonAdapter.clear();
                        moticonPositiveAdapter.clear();
                        moticonNegativeAdapter.clear();
                        moticonFunnyAdapter.clear();
                        moticonAnimalsAdapter.clear();
                        new getMoticons().execute();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onQueryTextChange(String search) {
        if (search.isEmpty()) {
            ((MoticonAdapter) recyclerView.getAdapter()).getFilter().filter("");
        } else {
            ((MoticonAdapter) recyclerView.getAdapter()).getFilter().filter(search.toLowerCase());
        }

        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        moticoinsItem = menu.findItem(R.id.action_moticoins);
        moticoinsItem.setTitle(String.format(getResources().getString(R.string.moticoins_value), appPreferences.getMoticoins()));

        return true;
    }

    public static void updateMoticoins(Context context) {
        AppPreferences appPreferences = MoticonsApplication.getAppPreferences();
        moticoinsItem.setTitle(String.format(context.getResources().getString(R.string.moticoins_value), appPreferences.getMoticoins()));
    }

    public static void setResultsMessage(Boolean result) {
        if (result) {
            noResults.setVisibility(View.VISIBLE);
            fastScroller.setVisibility(View.GONE);
        } else {
            noResults.setVisibility(View.GONE);
            fastScroller.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_moticoins:
                startActivity(new Intent(context, MoticoinsActivity.class));
                break;
            case R.id.action_moticoins_icon:
                startActivity(new Intent(context, MoticoinsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else if (searchItem.isVisible() && !searchView.isIconified()) {
            searchView.onActionViewCollapsed();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, R.string.tap_exit, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

}
