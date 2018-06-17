package book.lab.com.app_jobschedular;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.util.Random;

public class MyJobService extends JobService {

    private Handler mJobHandler = new Handler( new Handler.Callback() {
        @Override
        public boolean handleMessage( Message msg ) {
            int n = new Random().nextInt(100); // 0~99 亂數
            Toast.makeText( getApplicationContext(), "JobService task running : " + n, Toast.LENGTH_SHORT ).show();
            jobFinished((JobParameters) msg.obj, false);
            return true; // true : handleMessage 處理結束。
        }
    } );

    // 任務開始時會執行的回呼函示
    @Override
    public boolean onStartJob(JobParameters params ) {
        mJobHandler.sendMessage(Message.obtain(mJobHandler, 1, params));
        return true;
        /*
        * true : 表示要自行實施 jobFinished() 來結束任務
        * false : 該方法結束此次任務也結束
        * */
    }

    // 收到一個取消請求時的回呼函示
    @Override
    public boolean onStopJob( JobParameters params ) {
        mJobHandler.removeMessages( 1 );
        return false;
        /*
        * true : 收到取消請求時，若當下的任務也正在執行則會調用此方法
        * false : 收到取消請求時，若當下沒有正在執行的任務則不會調用此方法
        * */
    }

}
