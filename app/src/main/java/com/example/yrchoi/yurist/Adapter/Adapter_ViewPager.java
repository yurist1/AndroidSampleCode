package com.example.yrchoi.yurist.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yrchoi.yurist.R;

import java.util.ArrayList;

/**
 * Created by gbkim on 2017-12-22.
 */

public class Adapter_ViewPager extends PagerAdapter {

    ArrayList<String[]> getArrResult;
    LayoutInflater inflater;

    public Adapter_ViewPager(LayoutInflater inInflater, ArrayList<String[]> arrayList) {
        this.getArrResult = arrayList;
        this.inflater = inInflater;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        // 새로운 View 객체를 Layoutinflater를 이용해서 생서
        View view = inflater.inflate(R.layout.fragment_my_pt_list, null);

        // 메인에서 찾는거와 다르게 위에서 만들었던 View를 이용하여서 find를 하는 것 주의
        TextView tv_main_ptName = view.findViewById(R.id.tv_main_ptName);

        // 현재 position 번째 해당하는 정보 보여주기
        String getposition = getArrResult.get(0)[1];
        tv_main_ptName.setText(getposition);

        // ViewPager에 만들어 낸 View 추가
        container.addView(view);

        // 정보 세팅된 View를 리턴
        return view;
    }

    @Override
    public int getCount() {

        if (getArrResult.size() <= 4) {
            return getArrResult.size();
        } else {
            return 4;
        }
    }

    // 화면에 보이지 않는 view는 파괴해 메모리 관리
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        // viewPager에서 보이지 않는 view는 제거
        // 세번째 파라미터가 View 객체이지만 데이터 타입이 Objec여서 형변환 실시
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
