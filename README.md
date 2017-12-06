# permissionGrant
1.Android 运行时授权

2.增加小米手机没授权却返回已授权状态

3.使用方法：
 1.实现PermissionI接口
 
 2.private PermissionGrant mPermissionGrant;
    private   String[] PERMISSION_GETPERMISSIONDO = new String[] {"android.permission.READ_PHONE_STATE","android.permission.ACCESS_FINE_LOCATION"};
 
         mPermissionGrant = new PermissionGrant();//实例化
        mPermissionGrant.setPERMISSION_GETPERMISSIONDO(PERMISSION_GETPERMISSIONDO);//设置你需要的权限，必须写
        mPermissionGrant.ApplyPermission(MainActivityB.this,this);//申请权限
        mPermissionGrant.setContent("我是来要权限的，你不给，我就不走了");//可写可不写
