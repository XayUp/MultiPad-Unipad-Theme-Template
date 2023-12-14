package com.kimjisub.launchpad.theme.template;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.xayup.debug.CrashLog;

public class MainActivity extends Activity {

    public final String unipad_package = "com.kimjisub.launchpad";
    public final String multipad_package = "com.xayup.multipad";
    
    public Context context;
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        this.context = this;
        
        if(!CrashLog.CrashLog(this, ()-> {super.setContentView(R.layout.activity_main);})){
            startActivity();
        }
    }
    
    public void startActivity(){
        ((TextView) findViewById(R.id.skin_name_by_author)).setText(getString(R.string.theme_name) + " " + getString(R.string.by) + " " + getString(R.string.theme_author));
        
        findViewById(R.id.unipad_icon).setOnClickListener((view) -> {
            Intent start = context.getPackageManager().getLaunchIntentForPackage(unipad_package);
            if(start != null){
                context.startActivity(start);
            } else {
                openOnMarket(context, unipad_package);
                Toast.makeText(context, getString(R.string.app_not_installed), Toast.LENGTH_LONG).show();
            }
            Toast.makeText(context, getString(R.string.apply_skin_instruction_unipad), Toast.LENGTH_LONG).show();
        });
        findViewById(R.id.multipad_icon).setOnClickListener((view) -> {
            Intent start = context.getPackageManager().getLaunchIntentForPackage(multipad_package);
            if(start != null){
                context.startActivity(start);
            } else {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/XayUp/MultiPad/releases")));
                Toast.makeText(context, getString(R.string.app_not_installed), Toast.LENGTH_LONG).show();
            }
            Toast.makeText(context, getString(R.string.apply_skin_instruction_multipad), Toast.LENGTH_LONG).show();
        });
    }
    
    public void openOnMarket(Context context, String app_package_name){
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=".concat(app_package_name))));
        } catch (ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=".concat(app_package_name))));
        }
    }
}
