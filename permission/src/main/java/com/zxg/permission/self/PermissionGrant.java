package com.zxg.permission.self;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Author ：zxg on 2017/12/5 16:24
 * email : remotecountry@163.com
 * date : 2017/12/5
 * 运行时权限获取
 */

public class PermissionGrant {
    /**
     * 监听
     */
    private PermissionI mPermissionGrantPermissionI;

    private final static int REQUESTPERMISSION = 110;
    private String[] PERMISSION_GETPERMISSIONDO = new String[]{};
    private String content = "开启权限，竭诚为您服务！";

    private static final SimpleArrayMap<String, Integer> MIN_SDK_PERMISSIONS;

    static {
        MIN_SDK_PERMISSIONS = new SimpleArrayMap<String, Integer>(8);
        MIN_SDK_PERMISSIONS.put("com.android.voicemail.permission.ADD_VOICEMAIL", 14);
        MIN_SDK_PERMISSIONS.put("android.permission.BODY_SENSORS", 20);
        MIN_SDK_PERMISSIONS.put("android.permission.READ_CALL_LOG", 16);
        MIN_SDK_PERMISSIONS.put("android.permission.READ_EXTERNAL_STORAGE", 16);
        MIN_SDK_PERMISSIONS.put("android.permission.USE_SIP", 9);
        MIN_SDK_PERMISSIONS.put("android.permission.WRITE_CALL_LOG", 16);
        MIN_SDK_PERMISSIONS.put("android.permission.SYSTEM_ALERT_WINDOW", 23);
        MIN_SDK_PERMISSIONS.put("android.permission.WRITE_SETTINGS", 23);
    }

    public String[] getPERMISSION_GETPERMISSIONDO() {
        return PERMISSION_GETPERMISSIONDO;
    }

    /**
     * 设置需要获取的权限
     * @param PERMISSION_GETPERMISSIONDO
     */
    public void setPERMISSION_GETPERMISSIONDO(String[] PERMISSION_GETPERMISSIONDO) {
        this.PERMISSION_GETPERMISSIONDO = PERMISSION_GETPERMISSIONDO;
    }

    public String getContent() {
        return content;
    }

    /**
     * 设置提示语
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public PermissionGrant() {
    }

    /**
     * 获取权限
     *
     * @param mContext
     */
    public void ApplyPermission(final Activity mContext, PermissionI mPermissionI) {
        if(PERMISSION_GETPERMISSIONDO.length == 0){
            Toast.makeText(mContext,"您还没有设置您需要获取的权限哦",Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == mPermissionGrantPermissionI) {
            this.mPermissionGrantPermissionI = mPermissionI;
        }

        if (hasSelfPermissions(mContext, PERMISSION_GETPERMISSIONDO)) {
            if (null != mPermissionI) {
                mPermissionI.grantedPermissionAndDoYourThing();
            } else {
                Log.d("开始请求权限=", "mPermissionI==null");
            }
            Log.d("ApplyPermission", "有权限");
        } else {
            //申请权限
            Log.d("ApplyPermission", "没有权限");
            if (shouldShowRequestPermissionRationale(mContext,
                    PERMISSION_GETPERMISSIONDO)
                    ) {
                //弹窗说明为什么要授权
                if (null != mPermissionI) {
                    mPermissionI.onShowTationale();
                    showDialog(mContext, content, new ConFirmI() {
                        @Override
                        public void sure() {
                            getAppDetailSettingIntent(mContext);
                        }

                        @Override
                        public void cancel() {

                        }
                    });
                } else {
                    Log.d("弹窗授权=", "mPermissionI==null");
                }
            } else {
                if ("Xiaomi".equals(Build.MANUFACTURER)) {
                    Log.d("小米手机=", "没有授权并且不能弹窗");
                    ActivityCompat.requestPermissions(mContext, PERMISSION_GETPERMISSIONDO, REQUESTPERMISSION);
                } else {
                    //不是小米手机的进入这里
                    Log.d("华为等等手机=", "没有授权并且不能弹窗");
                    ActivityCompat.requestPermissions(mContext, PERMISSION_GETPERMISSIONDO, REQUESTPERMISSION);
                }

            }
        }
    }

    /**
     * 查询是否需要自己弹窗来说明授权
     *
     * @param activity
     * @param permissions
     * @return
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询一系列权限
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean hasSelfPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (permissionExists(permission) && !hasSelfPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限检查
     *
     * @param context
     * @param permission
     * @return
     */
    private static boolean hasSelfPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && "Xiaomi".equalsIgnoreCase(Build.MANUFACTURER)) {
            return hasSelfPermissionForXiaomi(context, permission);
        }
        try {
            return checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
        } catch (RuntimeException t) {
            return false;
        }
    }

    /**
     * 对小米的单独权限检查
     *
     * @param context
     * @param permission
     * @return
     */
    private static boolean hasSelfPermissionForXiaomi(Context context, String permission) {
        //原来代码
//        String permissionToOp = AppOpsManagerCompat.permissionToOp(permission);
//        if (permissionToOp == null) {
//            // in case of normal permissions(e.g. INTERNET)
//            return true;
//        }
//        int noteOp = AppOpsManagerCompat.noteOp(context, permissionToOp, Process.myUid(), context.getPackageName());
//        return noteOp == AppOpsManagerCompat.MODE_ALLOWED && checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;

        //小米手机拒绝后，会返回授权成功，以下代码可以检查出来是否授权，但是不会再弹出窗口了，需要设置为neverAskAgain
        if (PermissionChecker.checkPermission(context, permission, Binder.getCallingPid(), Binder.getCallingUid(), context.getPackageName()) == 0) {
            Log.e("小米手机通过", "授权");
            return true;
        } else {
            Log.e("小米手机不通过", "授权");
            return false;
        }

    }


    /**
     * 查询是否有此权限
     *
     * @param permission
     * @return
     */
    private static boolean permissionExists(String permission) {
        // Check if the permission could potentially be missing on this device
        Integer minVersion = MIN_SDK_PERMISSIONS.get(permission);
        // If null was returned from the above call, there is no need for a device API level check for the permission;
        // otherwise, we check if its minimum API level requirement is met
        return minVersion == null || Build.VERSION.SDK_INT >= minVersion;
    }

    /**
     * activity传入返回值
     *
     * @param mContext
     * @param requestCode
     * @param grantResults
     */
    public void onRequestPermissionsResult(final Activity mContext, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case REQUESTPERMISSION:
                if (verifyPermissions(mContext,grantResults,PERMISSION_GETPERMISSIONDO)) {
                    if (null != mPermissionGrantPermissionI) {
                        mPermissionGrantPermissionI.grantedPermissionAndDoYourThing();
                    } else {
                        Log.d("返回1=", "mPermissionI==null");
                    }
                } else {
                    if (shouldShowRequestPermissionRationale(mContext, PERMISSION_GETPERMISSIONDO)) {

                        if (null != mPermissionGrantPermissionI) {
                            mPermissionGrantPermissionI.onNeverAskAgain();
                            showDialog(mContext, content, new ConFirmI() {
                                @Override
                                public void sure() {
                                    getAppDetailSettingIntent(mContext);
                                }

                                @Override
                                public void cancel() {

                                }
                            });
                        } else {
                            Log.d("返回2=", "mPermissionI==null");
                        }
                    } else {
                        if (null != mPermissionGrantPermissionI) {
                            mPermissionGrantPermissionI.onPermissionDenied();
                            if ("Xiaomi".equals(Build.MANUFACTURER)) {
                                showDialog(mContext, content, new ConFirmI() {
                                    @Override
                                    public void sure() {
                                        getAppDetailSettingIntent(mContext);
                                    }

                                    @Override
                                    public void cancel() {

                                    }
                                });

                            }else {

                            }
                        } else {
                            Log.d("返回3=", "mPermissionI==null");
                        }

                    }
                }


                break;
            default:
                break;
        }
    }

    /**
     * 验证授权返回值
     *
     * @param grantResults
     * @return
     */
    public static boolean verifyPermissions(Context mContext, int[] grantResults, String[] PERMISSION_GETPERMISSIONDO) {
        if (grantResults.length == 0) {
            return false;
        }
        if ("Xiaomi".equals(Build.MANUFACTURER)) {
            //小米手机如果授权窗口弹出来之后什么操作都不做的话，会返回已授权
            return hasSelfPermissions(mContext, PERMISSION_GETPERMISSIONDO);
        } else {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 跳转权限设置页面
     * @param context
     */
    public static void getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);
    }

    /**
     * 统一弹窗样式
     * @param mContext
     * @param showContent
     * @param mConFirmI
     */
    public void showDialog(Context mContext, String showContent, final ConFirmI mConFirmI){
        new AlertDialog.Builder(mContext)
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        mConFirmI.sure();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        mConFirmI.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(showContent)
                .show();
    }

}
