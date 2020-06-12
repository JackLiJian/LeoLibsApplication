package com.leo.leolibsapplication.activity;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * 获取联系人
 */
public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onePermission();
    }


    /**只有一个运行时权限申请的情况*/
    private void onePermission(){
        RxPermissions rxPermissions = new RxPermissions(ContactsActivity.this); // where this is an Activity instance
        rxPermissions.request(Manifest.permission.READ_CONTACTS) //权限名称，多个权限之间逗号分隔开
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) { // 在android 6.0之前会默认返回true
                            getContacts();
                        } else {
                            // 未获取权限
                            Toast.makeText(ContactsActivity.this, "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void getContacts() {
        Cursor cursor = null;
        try {
            //查询联系人数据,使用了getContentResolver().query方法来查询系统的联系人的数据
            //CONTENT_URI就是一个封装好的Uri，是已经解析过得常量
            cursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );
            //对cursor进行遍历，取出姓名和电话号码
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //获取联系人姓名
                    String displayName = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    ));
                    //获取联系人手机号
                    String number = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    ));
                    Log.e("TAG","displayName="+displayName);
                    Log.e("TAG","number="+number);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //记得关掉cursor
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
