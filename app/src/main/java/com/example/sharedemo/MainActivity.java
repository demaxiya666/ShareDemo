package com.example.sharedemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private ShareActionProvider mShareActionProvider;
    private Button btn_sharetext;
    private Button btn_sharetextimprove;
    private Button btn_shareimage;
    private Button btn_shareimages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btn_sharetext = (Button) findViewById(R.id.btn_sharetext);
        btn_sharetextimprove = (Button) findViewById(R.id.btn_sharetextimprove);
        btn_shareimage = (Button) findViewById(R.id.btn_shareimage);
        btn_shareimages = (Button) findViewById(R.id.btn_shareimages);
        btn_sharetext.setOnClickListener(this);
        btn_sharetextimprove.setOnClickListener(this);
        btn_shareimage.setOnClickListener(this);
        btn_shareimages.setOnClickListener(this);

        //init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        //找到分享菜单的支持类对象

        return true;
    }
    //    //这里来设置share的数据，设置后share菜单将可用
//    public void setShareIntent(Intent shareIntent) {
//        if (mShareActionProvider != null) {
//            mShareActionProvider.setShareIntent(shareIntent);
//        }
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.menu_item_share) {
            Log.d("fnst","share");
            //mShareActionProvider  = (ShareActionProvider) item.getActionProvider();
            Toast.makeText(MainActivity.this,"share",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            //比较散乱的列出了所有的可以分享的应用
            case R.id.btn_sharetext: {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            }
            //分享选择列表,并且修改了分享列表的标题
            case  R.id.btn_sharetextimprove: {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
                break;
            }
            //分享图片(本地图片),未测试成功,因为图片不存在
            case  R.id.btn_shareimage: {
                //Toast.makeText(MainActivity.this,getPath,Toast.LENGTH_SHORT).show(); // 输出/storage/emulated/0
                //Toast.makeText(MainActivity.this,AbsolutePath,Toast.LENGTH_SHORT).show(); // 输出/storage/emulated/0
                //Toast.makeText(MainActivity.this,Name,Toast.LENGTH_SHORT).show();   // 输出0
                //Toast.makeText(MainActivity.this,Parent,Toast.LENGTH_SHORT).show();  // 输出/storage/emulated

                String imagePath = Environment.getExternalStorageDirectory() + File.separator + "ESFileExplorer.jpg";
                //由文件得到uri
                Uri imageUri = Uri.fromFile(new File(imagePath));
                Log.d("share", "uri:" + imageUri);  //输出：file:///storage/emulated/0/test.jpg
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
                break;
            }
            //分享多张图片(本地图片) 失败,没有这个本地文件
            case  R.id.btn_shareimages: {
                ArrayList uriimages = new ArrayList<>();
                String path = Environment.getExternalStorageDirectory() + File.separator;
                uriimages.add(Uri.fromFile(new File(path+"australia_1.jpg")));
                uriimages.add(Uri.fromFile(new File(path+"australia_2.jpg")));
                uriimages.add(Uri.fromFile(new File(path+"australia_3.jpg")));

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriimages);
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, "分享到"));
            }
        }
    }
    public void init(){
//        Intent intent = getIntent();
//        String action = intent.getAction();
//        String type = intent.getType();
//
//        if (Intent.ACTION_SEND.equals(action) && type != null) {
//            if ("text/plain".equals(type)) {
//                handleSendText(intent); // Handle text being sent
//            } else if (type.startsWith("image/")) {
//                handleSendImage(intent); // Handle single image being sent
//            }
//        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
//            if (type.startsWith("image/")) {
//                handleSendMultipleImages(intent); // Handle multiple images being sent
//            }
//        } else {
//            // Handle other intents, such as being started from the home screen
//        }
//    }
//    void handleSendText(Intent intent) {
//        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
//        String sharedTitle = intent.getStringExtra(Intent.EXTRA_TITLE);
//        if (sharedText != null) {
//            // Update UI to reflect text being shared
//            Toast.makeText(MainActivity.this,sharedText,Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    void handleSendImage(Intent intent) {
//        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
//        if (imageUri != null) {
//            //在处理二进制数据时，应当在一另外一个线程中去处理
//            // Update UI to reflect image being shared
//            Toast.makeText(MainActivity.this,imageUri.toString(),Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    void handleSendMultipleImages(Intent intent) {
//        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
//        if (imageUris != null) {
//            //在处理二进制数据时，应当在一另外一个线程中去处理
//            // Update UI to reflect multiple images being shared
//            Toast.makeText(MainActivity.this,imageUris.get(0).toString(),Toast.LENGTH_SHORT).show();
//        }
//    }
    }
}
