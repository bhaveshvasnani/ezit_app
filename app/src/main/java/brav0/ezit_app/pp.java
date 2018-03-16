package brav0.ezit_app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by User on 23-Jul-17.
 */

public class pp extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.pp,null);
        TextView y = (TextView) x.findViewById(R.id.pp);
        y.setText(Html.fromHtml("<p>Ezit and its team knows that you care how information about you is used and shared, and we appreciate your trust that we will do so carefully and sensibly. This notice describes our privacy policy. By visiting Ezit.com, you are accepting the practices described in this Privacy Notice.<br>•How we collect and use information<br>•Personal Information <br>•\tWhat about Cookies?<br>Any Concern over privacy policy <br><br>How we collect and use information<br>Information you provide us directly:<br><br>We ask for certain information such as your email id when you register to an Ezit application. The password you use to register is kept safe and is not disclosed to anyone for any purpose. We also store the messages send inside the app and the requests made by you. We do not disclose any of the information taken by us.<br><br>Personal Information<br>Information you provide us directly:<br>We ask for certain information such as your email id when you register to an Ezit application. The password you use to register is kept safe and is not disclosed to anyone for any purpose. We also store the messages send inside the app and the requests made by you. We do not disclose any of the information taken by us.<br><br>What about Cookies? <br>Information you provide us directly:<br>We ask for certain information such as your email id when you register to an Ezit application. The password you use to register is kept safe and is not disclosed to anyone for any purpose. We also store the messages send inside the app and the requests made by you. We do not disclose any of the information taken by us.<br><br>Any Concern over privacy policy<br>Information you provide us directly:<br>We ask for certain information such as your email id when you register to an Ezit application. The password you use to register is kept safe and is not disclosed to anyone for any purpose. We also store the messages send inside the app and the requests made by you. We do not disclose any of the information taken by us.<br><br>also read Terms Of Service along with privacy policy for best experience.</p>"));
        return x;
    }

}
