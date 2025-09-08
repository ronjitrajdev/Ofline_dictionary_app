package com.ronjit.banglaenglishdictionary;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.ronjit.banglaenglishdictionary.ad.InterstitialAdManager;
import com.ronjit.banglaenglishdictionary.book_fragment.Day_1_Fragment;
import com.ronjit.banglaenglishdictionary.book_fragment.Home_Word;
import com.ronjit.banglaenglishdictionary.book_fragment.Hospital_Word;
import com.ronjit.banglaenglishdictionary.book_fragment.Hotel_Word;
import com.ronjit.banglaenglishdictionary.book_fragment.Market_Word;
import com.ronjit.banglaenglishdictionary.book_fragment.Office_Word;
import com.ronjit.banglaenglishdictionary.book_fragment.Phone_Word;
import com.ronjit.banglaenglishdictionary.book_fragment.School_Word;
import com.ronjit.banglaenglishdictionary.book_fragment.Shopping_Word;
import com.ronjit.banglaenglishdictionary.book_fragment.Travel_Word;
import com.ronjit.banglaenglishdictionary.fragment.Note_Book;
import com.ronjit.banglaenglishdictionary.fragment.Privacy_Policy;
import com.ronjit.banglaenglishdictionary.fragment.BookmarkFragment;
import com.ronjit.banglaenglishdictionary.fragment.HomeFragment;
import com.ronjit.banglaenglishdictionary.nightmode.NightModeThemes;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer_layout;
    ImageView btn_menu, bookMark;
    public static TextView toolbar_title;
    NavigationView navigation_view;
    SwitchCompat switchCompat;
    LinearLayout home_nav , noteBook_nav, share_app_nav, privacyPolicy_nav, developerContact_nav;
    View headerView;
    private InterstitialAdManager interstitialAdManager;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that your app will not show notifications.
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //===============================================
        WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView())
                .setAppearanceLightStatusBars(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            getWindow().setNavigationBarContrastEnforced(false);
        }
        EdgeToEdge.enable(this);
        //============================================

        //=============================================
        setContentView(R.layout.activity_main);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //======================================================
        // Fragment Manager Listener ব্যাক স্ট্যাক পরিবর্তন হলে টুলবার আপডেট করবে
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_Layout);
            updateToolbarTitle(currentFragment);
        });

        initMobileAds.requestConsentForm(MainActivity.this);

        interstitialAdManager = new InterstitialAdManager(this, getString(R.string.INTERTITIAL_AD_UNIT_ID));
        //========================================================
        drawer_layout = findViewById(R.id.drawer_layout);
        btn_menu = findViewById(R.id.btn_menu);
        bookMark = findViewById(R.id.bookMark);
        toolbar_title = findViewById(R.id.toolbar_title);
        navigation_view = findViewById(R.id.navigation_view);

        //=====================================================
        headerView = navigation_view.getHeaderView(0);
        switchCompat = headerView.findViewById(R.id.switch_btn);
        home_nav = headerView.findViewById(R.id.home_nav);
        noteBook_nav = headerView.findViewById(R.id.noteBook);
        share_app_nav = headerView.findViewById(R.id.share_app);
        privacyPolicy_nav = headerView.findViewById(R.id.privacyPolicy_nav);
        developerContact_nav = headerView.findViewById(R.id.developerContact_nav);

        //==============================================================
        askNotificationPermission();
        //==============================================================




        setupNavigationDrawer();

        //====================================================
        switchCompat.setChecked(
                getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
                        .getBoolean("nightMode", false)
        );

        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            NightModeThemes nightModeThemes = new NightModeThemes(MainActivity.this);
            nightModeThemes.toggleNightMode(MainActivity.this); // context পাঠাও
            drawer_layout.closeDrawer(GravityCompat.START);
        });


        //===============================================

        //===============================================
        if (savedInstanceState == null) {
            // Check if the current fragment is already HomeFragment
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_Layout);
            clearBackStack();
            if (!(currentFragment instanceof HomeFragment)) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.fragment_Layout, new HomeFragment());
                ft.commit();
                toolbar_title.setText("Home");
            }
        }




        bookMark.setOnClickListener(v -> {
            replaceFragment(new BookmarkFragment(), "BookmarkFragment");
            toolbar_title.setText("Bookmark");
        });



        //============================
        btn_menu.setOnClickListener(view -> {
            toggleDrawer();
        });

        //========================================================
        // Call handleIntent() for initial Intent
        handleIntent(getIntent());
        //================================
        // OnBack Pressed Method --------------
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START);
                    return; // Drawer বন্ধ করার পর back pressed আর কিছু করবে না
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                } else {
                    Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_Layout);
                    if (currentFragment instanceof HomeFragment) {
                        showExitConfirmationDialog();
                    } else {
                        // হোম ফ্র্যাগমেন্টে ফিরে যান
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_Layout, new HomeFragment())
                                .commit();
                    }
                }
            }
        });

    }  // Oncreate Ends ---------------------
    //==================================================

    //==================================
    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", (dialog, which) -> finish()) // Yes চাপলে অ্যাপ বন্ধ হবে
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss()) // No চাপলে ডায়ালগ বন্ধ হবে
                .show();
    }

    private void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    // ফ্র্যাগমেন্ট ইন্সট্যান্স তৈরি করার আগে পরীক্ষা করুন
    private void replaceFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        // BackStack ক্লিয়ার করা
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_Layout, fragment, tag);
        ft.addToBackStack(null); // ব্যাকস্ট্যাক যোগ না করলে আগের ফ্র্যাগমেন্ট থাকবে না
        ft.commit();
    }

    private void fragmentString(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_Layout, fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }

    //==========================================
    @Override
    protected void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent); // Handle Intent data
    }

    // Ei method onCreate() theke o call hobe
    private void handleIntent(Intent intent) {
        int dayFragmentNumber = intent.getIntExtra("open_day_fragment", -1);

        if (dayFragmentNumber == -1) return; // invalid number

        switch (dayFragmentNumber) {
            case 1:
                fragmentString(new Day_1_Fragment(), "Daily_English_Word");
                toolbar_title.setText("Daily Sentence");
                break;
            case 2:
                fragmentString(new Home_Word(), "Home_Word");
                toolbar_title.setText("Home Sentence");
                break;
            case 3:
                fragmentString(new Hospital_Word(), "Hospital_Word");
                toolbar_title.setText("Hospital Sentence");
                break;
            case 4:
                fragmentString(new Hotel_Word(), "Hotel_Word");
                toolbar_title.setText("Hotel Sentence");
                break;
            case 5:
                fragmentString(new Market_Word(), "Market_Word");
                toolbar_title.setText("Market Sentence");
                break;
            case 6:
                fragmentString(new Office_Word(), "Office_Word");
                toolbar_title.setText("Office Sentence");
                break;
            case 7:
                fragmentString(new Phone_Word(), "Phone_Word");
                toolbar_title.setText("Phone Call Sentence");
                break;
            case 8:
                fragmentString(new School_Word(), "School_Word");
                toolbar_title.setText("School Sentence");
                break;
            case 9:
                fragmentString(new Shopping_Word(), "Shopping_Word");
                toolbar_title.setText("Shopping Sentence");
                break;
            case 10:
                fragmentString(new Travel_Word(), "Travel_Word");
                toolbar_title.setText("Travel Sentence");
                break;
            default:
                // কোনো invalid number এলে default fragment দেখানো যায়
                fragmentString(new Day_1_Fragment(), "Daily_English_Word");
                toolbar_title.setText("Daily Sentence");
                break;
        }
    }


    //===============================================
    private void setupNavigationDrawer() {

        home_nav.setOnClickListener(view -> {
            openHomeFragment(); // Home Fragment খুলবে
            drawer_layout.closeDrawer(GravityCompat.START);
        });

        //------------------------

        noteBook_nav.setOnClickListener(v -> {
            replaceFragment(new Note_Book(), "Note Book");
            drawer_layout.closeDrawer(GravityCompat.START);
        });

        share_app_nav.setOnClickListener(v -> {
            shareApp(MainActivity.this);
        });
        //------------------------------------------------
        privacyPolicy_nav.setOnClickListener(view -> {
            replaceFragment(new Privacy_Policy(), "Privacy Policy");
            drawer_layout.closeDrawer(GravityCompat.START);
        });


        developerContact_nav.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ronjitraj11@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "My subject");
            startActivity(Intent.createChooser(intent, "Email via..."));

            drawer_layout.closeDrawer(GravityCompat.START);
        });
    }


    private void toggleDrawer() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            drawer_layout.openDrawer(GravityCompat.START);

        }
    }

    private void updateToolbarTitle(Fragment currentFragment) {
        if (currentFragment instanceof HomeFragment) {
            toolbar_title.setText("English Learning");
        } else if (currentFragment instanceof BookmarkFragment) {
            toolbar_title.setText("Bookmark");
        } else if (currentFragment instanceof Note_Book) {
            toolbar_title.setText("Note Book");
        } else if (currentFragment instanceof Privacy_Policy) {
            toolbar_title.setText("Privacy Policy");
        }
    }

    //============================================
    private void openHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        clearBackStack();
        Fragment homeFragment = fragmentManager.findFragmentByTag("HOME_FRAGMENT");

        if (homeFragment != null) {
            // সব ফ্র্যাগমেন্ট hide করবো
            for (Fragment fragment : fragmentManager.getFragments()) {
                transaction.hide(fragment);
            }

            // HomeFragment দেখাবো
            transaction.show(homeFragment);
            transaction.commit();

            toolbar_title.setText("English Learning"); // টুলবার টাইটেল আপডেট করবো
        }
    }

    //==================================================



    //======================================
    private void shareApp(Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Download now : https://play.google.com/store/apps/details?id" + appPackageName);
        sendIntent.setType("text/ plain");
        context.startActivity(sendIntent);
    }

    //======================================
    private Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager.findFragmentById(R.id.fragment_Layout); // অথবা fragment_container id
    }

    //===============================================

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                // Permission already granted
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("Notifications Permission")
                        .setMessage("The app requires this permission to send notifications.")
                        .setPositiveButton("Yes", (dialog, which) -> requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS))
                        .setNegativeButton("No thanks", (dialog, which) -> {
                            // Handle user declining the permission
                        })
                        .create().show();
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }
    //==============================================

} // public class ends-------------------------