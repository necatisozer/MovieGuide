package com.esoxjem.movieguide.movies.licence

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import com.esoxjem.movieguide.BaseApplication
import com.esoxjem.movieguide.R
import kotlinx.android.synthetic.main.dialog_licence.view.*
import javax.inject.Inject

/**
 * @author necatisozer
 */
class LicenceDialogFragment : DialogFragment(), LicenceContract.View {

    @Inject
    lateinit var presenter: LicenceContract.Presenter

    lateinit var dialogView: View

    companion object {
        fun newInstance(): LicenceDialogFragment {
            return LicenceDialogFragment().apply { isCancelable = false }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        (activity?.application as BaseApplication).listingComponent.inject(this)
        presenter.setView(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        dialogView = inflater.inflate(R.layout.dialog_licence, null)

        val dialog = Dialog(activity)
        return dialog.apply {
            setContentView(dialogView)
            setTitle(R.string.licence_key_title)
            show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        presenter.checkAuthentication()
        dialogView.licence_key_confirm_button.setOnClickListener {
            presenter.checkLicenceKey(dialogView.licence_key_input_edit_text.text.toString())
        }
    }

    override fun dismissDialog() {
        dismiss()
    }

    override fun showSuccess() {
        dismiss()
        context?.let {
            AlertDialog.Builder(it)
                    .setTitle(R.string.licence_key_success_title)
                    .setMessage(R.string.licence_key_success_message)
                    .setNeutralButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()
        }

    }

    override fun showError() {
        dialogView.licence_key_input_edit_text.setError(getString(R.string.licence_key_error_message))
    }

    override fun onDestroyView() {
        presenter.destroy()
        super.onDestroyView()
    }
}
