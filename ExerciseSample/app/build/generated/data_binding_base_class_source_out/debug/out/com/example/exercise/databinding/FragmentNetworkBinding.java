// Generated by view binder compiler. Do not edit!
package com.example.exercise.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.wear.widget.BoxInsetLayout;
import com.example.exercise.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentNetworkBinding implements ViewBinding {
  @NonNull
  private final BoxInsetLayout rootView;

  @NonNull
  public final TextView deviceconnectionStatusTv;

  @NonNull
  public final TextInputEditText messagecontentEditText;

  @NonNull
  public final TextView messagelogTextView;

  @NonNull
  public final ScrollView scrollviewTextMessageLog;

  @NonNull
  public final Button sendmessageButton;

  @NonNull
  public final TextInputLayout textInputLayout;

  private FragmentNetworkBinding(@NonNull BoxInsetLayout rootView,
      @NonNull TextView deviceconnectionStatusTv, @NonNull TextInputEditText messagecontentEditText,
      @NonNull TextView messagelogTextView, @NonNull ScrollView scrollviewTextMessageLog,
      @NonNull Button sendmessageButton, @NonNull TextInputLayout textInputLayout) {
    this.rootView = rootView;
    this.deviceconnectionStatusTv = deviceconnectionStatusTv;
    this.messagecontentEditText = messagecontentEditText;
    this.messagelogTextView = messagelogTextView;
    this.scrollviewTextMessageLog = scrollviewTextMessageLog;
    this.sendmessageButton = sendmessageButton;
    this.textInputLayout = textInputLayout;
  }

  @Override
  @NonNull
  public BoxInsetLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentNetworkBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentNetworkBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_network, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentNetworkBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.deviceconnectionStatusTv;
      TextView deviceconnectionStatusTv = ViewBindings.findChildViewById(rootView, id);
      if (deviceconnectionStatusTv == null) {
        break missingId;
      }

      id = R.id.messagecontentEditText;
      TextInputEditText messagecontentEditText = ViewBindings.findChildViewById(rootView, id);
      if (messagecontentEditText == null) {
        break missingId;
      }

      id = R.id.messagelogTextView;
      TextView messagelogTextView = ViewBindings.findChildViewById(rootView, id);
      if (messagelogTextView == null) {
        break missingId;
      }

      id = R.id.scrollviewTextMessageLog;
      ScrollView scrollviewTextMessageLog = ViewBindings.findChildViewById(rootView, id);
      if (scrollviewTextMessageLog == null) {
        break missingId;
      }

      id = R.id.sendmessageButton;
      Button sendmessageButton = ViewBindings.findChildViewById(rootView, id);
      if (sendmessageButton == null) {
        break missingId;
      }

      id = R.id.textInputLayout;
      TextInputLayout textInputLayout = ViewBindings.findChildViewById(rootView, id);
      if (textInputLayout == null) {
        break missingId;
      }

      return new FragmentNetworkBinding((BoxInsetLayout) rootView, deviceconnectionStatusTv,
          messagecontentEditText, messagelogTextView, scrollviewTextMessageLog, sendmessageButton,
          textInputLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}