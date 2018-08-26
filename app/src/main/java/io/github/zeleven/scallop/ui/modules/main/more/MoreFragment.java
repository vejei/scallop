package io.github.zeleven.scallop.ui.modules.main.more;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.ui.modules.about.AboutActivity;
import io.github.zeleven.scallop.ui.base.BaseFragment;
import io.github.zeleven.scallop.ui.modules.settings.SettingsActivity;
import io.github.zeleven.scallop.utils.Constants;

public class MoreFragment extends BaseFragment implements MoreContract.View {
    @Inject MorePresenter presenter;

    @BindView(R.id.feedback_item) LinearLayout feedbackItem;
    @BindView(R.id.dark_mode_item) LinearLayout darkModeItem;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_more;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();

        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @OnClick(R.id.feedback_item)
    public void sendFeedback() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + Constants.MY_EMAIL));
        String emailSubject = context.getResources().getString(R.string.feedback);
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        context.startActivity(intent);
    }

    @OnClick(R.id.settings_item)
    public void openSettingsActivity() {
        startActivity(new Intent(getContext(), SettingsActivity.class));
    }

    @OnClick(R.id.info_item)
    public void openAboutActivity() {
        startActivity(new Intent(getContext(), AboutActivity.class));
    }
}
