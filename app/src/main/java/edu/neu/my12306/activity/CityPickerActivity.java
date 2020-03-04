package edu.neu.my12306.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my12306.R;
import edu.neu.my12306.adapter.CYBChangeCityGridViewAdapter;
import edu.neu.my12306.adapter.ContactAdapter;
import edu.neu.my12306.entity.UserEntity;
import edu.neu.my12306.util.QGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableHeaderAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

public class CityPickerActivity extends AppCompatActivity {
    //自定义的城市选择器头部的适配器
    private BannerHeaderAdapter mBannerHeaderAdapter;
    //自定义的热门城市列表
    private CYBChangeCityGridViewAdapter cybChangeCityGridViewAdapter;
    //城市列表适配器
    private ContactAdapter mAdapter;
    //热门城市列表
    private String[] city ={"沈阳","北京","广州","唐山","大连","石家庄","鞍山","张家口","秦皇岛","杭州","长沙","中山"};
    private IndexableLayout indexableLayout;
    private ArrayList<String> list;
    private ImageView pic_contact_back;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        getSupportActionBar().hide();
        initView();
        initAdapter();
        onlisten();
    }


    public void initView(){
        intent = getIntent();
        pic_contact_back = findViewById(R.id.pic_contact_back);
        indexableLayout = findViewById(R.id.indexableLayout);
        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
    }

    public void initAdapter(){
        mAdapter = new ContactAdapter(this);
        indexableLayout.setAdapter(mAdapter);
        indexableLayout.setOverlayStyle_Center();
        mAdapter.setDatas(initDatas());

        indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);

        List<String> bannerList = new ArrayList<>();
        bannerList.add("");
        mBannerHeaderAdapter = new BannerHeaderAdapter("↑",null,bannerList);

        //增加头部适配器
        indexableLayout.addHeaderAdapter(mBannerHeaderAdapter);

    }

    public void onlisten(){
        pic_contact_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<UserEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, UserEntity entity) {
                if(originalPosition>=0){
                    intent.putExtra("address",entity.getNick());
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    Toast.makeText(CityPickerActivity.this, "选中Header/Footer："+entity.getNick()+" 当前位置："+currentPosition, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //头部适配器，包括地点定位和热门城市列表
    class BannerHeaderAdapter extends IndexableHeaderAdapter{
        private static final int TYPE = 1;

        public BannerHeaderAdapter(String index, String indexTitle, List datas) {
            super(index, indexTitle, datas);
        }

        @Override
        public int getItemViewType() {
            return TYPE;
        }

        @Override
        public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
            //渲染视图，使用cityheader布局文件。
            View view = LayoutInflater.from(CityPickerActivity.this).inflate(R.layout.city_header,parent,false);
            VH holder = new VH(view);
            return holder;
        }

        @Override
        public void onBindContentViewHolder(RecyclerView.ViewHolder holder, Object entity) {
            //初始化热门城市列表和城市定位
            final VH vh = (VH) holder;
            list  = new ArrayList<>();
            for (int i=0;i<city.length;i++){
                list.add(city[i]);
            }
            //初始化热门城市列表，设置适配器，增加点击事件。
            cybChangeCityGridViewAdapter = new CYBChangeCityGridViewAdapter(CityPickerActivity.this,list);
            vh.head_home_change_city_gridview.setAdapter(cybChangeCityGridViewAdapter);
            vh.head_home_change_city_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    intent.putExtra("address",list.get(position));
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
            //初始化定位城市，增加点击事件
            vh.item_header_city_sy.setText(intent.getStringExtra("current_city"));
            vh.item_header_city_sy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("address",vh.item_header_city_sy.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

        }
        //viewHolder用来进行列表缓存
        private class VH extends RecyclerView.ViewHolder{
            GridView head_home_change_city_gridview;
            TextView item_header_city_sy;
            public VH(View itemView){
                super(itemView);
                head_home_change_city_gridview = (QGridView)itemView.findViewById(R.id.item_header_city_gridview);
                item_header_city_sy = itemView.findViewById(R.id.item_header_city_sy);
            }
        }
    }

    private List<UserEntity> initDatas() {
        List<UserEntity> list = new ArrayList<>();
        // 初始化数据
        List<String> contactStrings = Arrays.asList(getResources().getStringArray(R.array.station_choice));
        List<String> mobileStrings = Arrays.asList(getResources().getStringArray(R.array.station_choice));
        for (int i = 0; i < contactStrings.size(); i++) {
            UserEntity contactEntity = new UserEntity(contactStrings.get(i), mobileStrings.get(i));
            list.add(contactEntity);
        }
        return list;
    }



}
