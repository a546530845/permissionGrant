//package com.zxg.permissiontest;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.Toast;
//
//import permissions.dispatcher.NeedsPermission;
//import permissions.dispatcher.OnNeverAskAgain;
//import permissions.dispatcher.OnPermissionDenied;
//import permissions.dispatcher.OnShowRationale;
//import permissions.dispatcher.RuntimePermissions;
//
//
//@RuntimePermissions
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        MainActivityPermissionsDispatcher.needPermissionWithCheck(this);
//        Log.d("检查权限","检查权限");
//        Log.e("手机厂商",Build.MANUFACTURER);
//    }
//
//    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void needPermission() {
//        //权限通过之后的操作
//        Log.d("needPermission","申请权限通过");
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//    }
//
//    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void onShowTationale(final PermissionRequest request) {
//        request.cancel();
////        new AlertDialog.Builder(this)
////                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(@NonNull DialogInterface dialog, int which) {
////                        request.proceed();
////                    }
////                })
////                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(@NonNull DialogInterface dialog, int which) {
////                        request.cancel();
////                    }
////                })
////                .setCancelable(false)
////                .setMessage("我是来申请权限的")
////                .show();
//    }
//
//    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void onPermissionDenied() {
//        Toast.makeText(this, "有权限不支持", Toast.LENGTH_SHORT).show();
//        Log.d("onPermissionDenied","有权限不支持");
//        getAppDetailSettingIntent(this);
//    }
//
//    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void onNeverAskAgain() {
//        Log.d("onNeverAskAgain","有权限设置永不访问");
//        getAppDetailSettingIntent(this);
//    }
//    public static void getAppDetailSettingIntent(Context context) {
//        Intent localIntent = new Intent();
//        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (Build.VERSION.SDK_INT >= 9) {
//            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
//            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
//        } else if (Build.VERSION.SDK_INT <= 8) {
//            localIntent.setAction(Intent.ACTION_VIEW);
//            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
//            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
//        }
//        context.startActivity(localIntent);
//    }
//}
