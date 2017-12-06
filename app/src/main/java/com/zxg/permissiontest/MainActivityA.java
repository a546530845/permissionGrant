//package com.zxg.permissiontest;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.Toast;
//
//import permissions.dispatcher.NeedsPermission;
//import permissions.dispatcher.OnNeverAskAgain;
//import permissions.dispatcher.OnPermissionDenied;
//import permissions.dispatcher.OnShowRationale;
//import permissions.dispatcher.PermissionRequest;
//import permissions.dispatcher.RuntimePermissions;
//
//
//@RuntimePermissions
//public class MainActivityA extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        MainActivityAPermissionsDispatcher.getPermissionDoWithCheck(this);
//        Log.d("检查权限","检查权限");
//        Log.e("手机厂商",Build.MANUFACTURER);
//    }
//
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
//
//    @NeedsPermission(Manifest.permission.READ_PHONE_STATE)
//    public void getPermissionDo() {
//        //权限通过之后的操作
//        Log.d("needPermission","申请权限通过");
//        Log.d("IMEI=",About_phone.getDeviceIMEI(this));
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        MainActivityAPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//    }
//
//    @OnShowRationale(Manifest.permission.READ_PHONE_STATE)
//    public void onShowTationale(final PermissionRequest request) {
//        Log.d("onShowTationale","有权限需要弹窗提示");
//        request.proceed();
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
//    @OnPermissionDenied(Manifest.permission.READ_PHONE_STATE)
//    public void onPermissionDenied() {
//        Toast.makeText(this, "有权限不支持", Toast.LENGTH_SHORT).show();
//        Log.d("onPermissionDenied","有权限不支持");
//        getAppDetailSettingIntent(this);
//    }
//
//    @OnNeverAskAgain(Manifest.permission.READ_PHONE_STATE)
//    public void onNeverAskAgain() {
//        Log.d("onNeverAskAgain","有权限设置永不访问");
//        new AlertDialog.Builder(this)
//                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(@NonNull DialogInterface dialog, int which) {
//                        getAppDetailSettingIntent(MainActivityA.this);
//                    }
//                })
//                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(@NonNull DialogInterface dialog, int which) {
//                    }
//                })
//                .setCancelable(false)
//                .setMessage("我是来申请权限的")
//                .show();
//    }
//}
