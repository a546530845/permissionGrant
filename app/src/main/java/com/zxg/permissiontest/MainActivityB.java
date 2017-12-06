package com.zxg.permissiontest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.zxg.permission.self.PermissionGrant;
import com.zxg.permission.self.PermissionI;

/**
 * Author ：zxg on 2017/12/5 17:13
 * email : remotecountry@163.com
 * date : 2017/12/5
 * self授权测试
 */

public class MainActivityB extends AppCompatActivity implements PermissionI{
    private PermissionGrant mPermissionGrant;
    private   String[] PERMISSION_GETPERMISSIONDO = new String[] {"android.permission.READ_PHONE_STATE","android.permission.ACCESS_FINE_LOCATION"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPermissionGrant = new PermissionGrant();//实例化
        mPermissionGrant.setPERMISSION_GETPERMISSIONDO(PERMISSION_GETPERMISSIONDO);//设置你需要的权限，必须写
        mPermissionGrant.ApplyPermission(MainActivityB.this,this);//申请权限
        mPermissionGrant.setContent("我是来要权限的，你不给，我就不走了");//可写可不写

    }

    @Override
    public void grantedPermissionAndDoYourThing() {
        Log.d("needPermission","申请权限通过");
        Log.d("IMEI=",About_phone.getDeviceIMEI(this));
    }

    @Override
    public void onShowTationale() {
        Log.d("onShowTationale","有权限需要弹窗提示");
    }

    @Override
    public void onPermissionDenied() {
        Toast.makeText(this, "有权限不支持", Toast.LENGTH_SHORT).show();
        Log.d("onPermissionDenied","有权限不支持");
    }

    @Override
    public void onNeverAskAgain() {
        Log.d("onNeverAskAgain","有权限设置永不访问");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionGrant.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}
