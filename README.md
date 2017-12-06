# permissionGrant
1.Android 运行时授权

2.增加小米手机没授权却返回已授权状态

3.使用方法：
 1.Activity中实现PermissionI接口
 
 2.private PermissionGrantForActivity mPermissionGrant;
    private   String[] PERMISSION_GETPERMISSIONDO = new String[] {"android.permission.READ_PHONE_STATE","android.permission.ACCESS_FINE_LOCATION"};
 
  mPermissionGrant = new PermissionGrantForActivity();//实例化
        mPermissionGrant.setPERMISSION_GETPERMISSIONDO(PERMISSION_GETPERMISSIONDO);//设置你需要的权限，必须写
        mPermissionGrant.ApplyPermission(MainActivityB.this,this);//申请权限
        mPermissionGrant.setContent("我是来要权限的，你不给，我就不走了");//可写可不写
        
   @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionGrant.onRequestPermissionsResult(this,requestCode,grantResults);
    }
        
        
3.fragment中实现PermissionI接口
4.private PermissionGrantForFragment mPermissionGrant;
    private   String[] PERMISSION_GETPERMISSIONDO = new String[] {"android.permission.READ_PHONE_STATE","android.permission.ACCESS_FINE_LOCATION"};
 mPermissionGrant = new PermissionGrantForFragment();//实例化
        mPermissionGrant.setPERMISSION_GETPERMISSIONDO(PERMISSION_GETPERMISSIONDO);//设置你需要的权限，必须写
        mPermissionGrant.ApplyPermission(MainFragment.this,this);//申请权限
        mPermissionGrant.setContent("我是来要权限的，你不给，我就不走了");//可写可不写
  
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionGrant.onRequestPermissionsResult(this,requestCode,grantResults);
    }
