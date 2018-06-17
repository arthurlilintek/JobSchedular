package book.lab.com.app_jobschedular;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    private JobScheduler mJobScheduler;
    private Context context;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_main);
        context = this;

        mJobScheduler = (JobScheduler) getSystemService( Context.JOB_SCHEDULER_SERVICE );

    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.schedule_job:

                ComponentName jobService = new ComponentName(getPackageName(), MyJobService.class.getName());

                JobInfo.Builder builder = new JobInfo.Builder( 1, jobService);
                builder.setPeriodic(3000);

                if( mJobScheduler.schedule( builder.build() ) <= 0 ) {
                    Toast.makeText(context, "排程建立失敗", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.cancel_job:
                mJobScheduler.cancel(1);
                //mJobScheduler.cancelAll();
                break;
        }
    }
}
