package com.jrjz_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.common.base.BaseActivity;
import com.jrjz_project.common.utils.ToastUtils;

/**
 * 充值页面
 */
public class TopUpActivity extends BaseActivity {

    private Intent mIntent;
    private String typeForTopUp; //判断是否是用户输入充值金额

    private TextView tv_top_up_chongzhi;
    private EditText et_top_up_chongzhi;
    private ImageView img_top_up_weixin;
    private ImageView img_top_up_zhifubao;
    private TextView tv_top_up_ok; //确定按钮
    private static String typeForPay = null; //支付方式
    private static final String WEIXIN = "weixin";
    private static final String ZHIFUBAO = "zhifubao";

    private String moneyForUserInput; //用户输入的充值金额


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        et_top_up_chongzhi = (EditText) findViewById(R.id.et_top_up_chongzhi);
        tv_top_up_chongzhi = (TextView) findViewById(R.id.tv_top_up_chongzhi);
        img_top_up_weixin = (ImageView) findViewById(R.id.img_top_up_weixin);
        img_top_up_zhifubao = (ImageView) findViewById(R.id.img_top_up_zhifubao);
        tv_top_up_ok = (TextView) findViewById(R.id.tv_top_up_ok);

        img_top_up_weixin.setOnClickListener(this);
        img_top_up_zhifubao.setOnClickListener(this);
        tv_top_up_ok.setOnClickListener(this);

        mIntent = getIntent();
        typeForTopUp = mIntent.getStringExtra("TypeForTopUp");
        if (!TextUtils.isEmpty(typeForTopUp)) {
            //当是固定的金额时
            if ("fixed".equals(typeForTopUp)) {
                tv_top_up_chongzhi.setVisibility(View.VISIBLE);
                et_top_up_chongzhi.setVisibility(View.GONE);
                //当需要用户输入金额时
            } else if ("WriteForUser".equals(typeForTopUp)) {
                tv_top_up_chongzhi.setVisibility(View.GONE);
                et_top_up_chongzhi.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void initView() {


    }

    @Override
    public void initData() {


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_top_up_weixin:  //微信支付
//                ToastUtils.showShort(TopUpActivity.this,"微信支付");
                img_top_up_weixin.setImageResource(R.drawable.dianjixdpi_03);
                img_top_up_zhifubao.setImageResource(R.drawable.weidianjixdpi_03);
                typeForPay = WEIXIN;
                break;
            case R.id.img_top_up_zhifubao: //支付宝支付
//                ToastUtils.showShort(TopUpActivity.this,"支付宝支付");
                img_top_up_weixin.setImageResource(R.drawable.weidianjixdpi_03);
                img_top_up_zhifubao.setImageResource(R.drawable.dianjixdpi_03);
                typeForPay = ZHIFUBAO;
                break;
            case R.id.tv_top_up_ok: //确定按钮 确定支付方式

                if (WEIXIN.equals(typeForPay)) {
                    ToastUtils.showShort(TopUpActivity.this, "支付方式：微信支付");
                } else if (ZHIFUBAO.equals(typeForPay)) {
                    ToastUtils.showShort(TopUpActivity.this, "支付方式：支付宝支付");
                } else {
                    ToastUtils.showShort(TopUpActivity.this, "未选择支付方式！");
                }


                break;
        }
    }
}
