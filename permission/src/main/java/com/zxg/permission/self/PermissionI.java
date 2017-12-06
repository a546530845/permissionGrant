package com.zxg.permission.self;

/**
 * Author ：zxg on 2017/12/5 16:29
 * email : remotecountry@163.com
 * date : 2017/12/5
 */

public interface PermissionI {
    /**
     * 得到权限，去做自己的事
     */
    public void grantedPermissionAndDoYourThing();

    /**
     * 弹窗显示(已经做好基本操作，可以不用写代码)
     */
    public void onShowTationale();

    /**
     * 权限拒绝（做好小米的操作，弹窗提示）
     */
    public void onPermissionDenied();

    /**
     * 禁止权限，并且不再提示（已经做好基本操作，可以不用写代码）
     */
    public void onNeverAskAgain();
}
