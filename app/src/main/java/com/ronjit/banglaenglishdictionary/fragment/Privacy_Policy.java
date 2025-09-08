package com.ronjit.banglaenglishdictionary.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ronjit.banglaenglishdictionary.R;

public class Privacy_Policy extends Fragment {

    private TextView privacyPolicy_TEXT;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_privacy__policy, container, false);
        privacyPolicy_TEXT = myView.findViewById(R.id.privacyPolicy_TEXT);

        String privacyPolicy =
                "Last Updated: June 5, 2025\n\n" +
                        "Ronjit Studio built the Bangla English Dictionary app as a Commercial app. This Service is provided by Ronjit Studio and is intended for use as is.\n" +
                        "This page is used to inform users regarding our policies with the collection, use, and disclosure of personal information for those who choose to use our Service.\n" +
                        "By using this Service, you agree to the collection and use of information in accordance with this policy. The personal information we collect is used solely to provide and improve the Service. We do not use or share your information with anyone except as described in this Privacy Policy.\n" +
                        "The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, unless otherwise defined here.\n\n" +

                        "Information Collection and Use\n" +
                        "For a better experience, while using our Service, we may require you to provide us with certain personally identifiable information, including but not limited to Device IDs. The information that we request will be retained by and used as described in this privacy policy.\n" +
                        "The app does use third party services that may collect information used to identify you.\n\n" +
                        "Link to privacy policy of third party service providers used by the app:\n" +
                        "▪ Google Play Services\n" +
                        "▪ AdMob\n" +
                        "▪ Google Analytics for Firebase\n\n" +

                        "Log Data\n" +
                        "We want to inform you that whenever you use our Service, in a case of an error in the app we collect data and information (through third party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (\"IP\") address, device name, operating system version, the configuration of the app when utilizing our Service, the time and date of your use of the Service, and other statistics.\n\n" +

                        "Cookies\n" +
                        "This app does not directly use cookies. However, some third-party code or SDKs (like AdMob or Firebase) may use cookies to collect data and improve their services. You can accept or refuse these cookies via device settings. Refusing cookies may affect some features of the app.\n\n" +

                        "Service Providers\n" +
                        "We may employ third-party companies and individuals to:\n" +
                        "• Facilitate the Service\n" +
                        "• Provide the Service on our behalf\n" +
                        "• Perform Service-related tasks\n" +
                        "• Assist us in analyzing app usage\n" +
                        "These third parties may have access to your personal data to perform specific tasks but are obligated not to disclose or misuse it.\n\n" +

                        "Security\n" +
                        "We value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and we cannot guarantee its absolute security.\n\n" +

                        "Links to Other Sites\n" +
                        "This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by us. Therefore, we strongly advise you to review the Privacy Policy of these websites. We have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services.\n\n" +

                        "Children’s Privacy\n" +
                        "This app is not intended for children under the age of 13. We do not knowingly collect personal information from children. If we discover that a child under 13 has provided us with personal data, we will delete it promptly. Parents or guardians may contact us if they believe their child has shared data with us.\n\n" +

                        "Data Deletion Right\n" +
                        "You have the right to access, rectify, object to, or erase the data maintained by us. You may request a change / delete to your personal data by contacting us by referring the issue via email.\n" +
                        "Email: ronjit.helpdesk@gmail.com\n" +
                        "If you believe our processing of your personal data infringes data protection laws, you have a legal right to initiate a complaint with a supervisory authority. Don't hesitate to contact us if you find any issue.\n\n" +

                        "Changes to This Privacy Policy\n" +
                        "We may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. We will notify you of any changes by posting the new Privacy Policy on this page.\n" +
                        "Effective Date: June 5, 2025\n\n" +

                        "Contact Us\n" +
                        "If you have any questions or concerns about this Privacy Policy, please contact us:\n" +
                        "📧 ronjit.helpdesk@gmail.com";

        privacyPolicy_TEXT.setText(privacyPolicy);

        return myView;
    }
}