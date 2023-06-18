package com.lmio.mlib.ui.auth

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lmio.mlib.R
import com.lmio.mlib.databinding.FragmentLoginBinding
import com.lmio.mlib.entity.Account
import com.lmio.mlib.utils.Requests
import com.lmio.mlib.utils.SnackbarUtil
import com.lmio.mlib.utils.Valid

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var interactionListener: FragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        interactionListener?.updateTitle("登录")
        setupViews()
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentInteractionListener) {
            interactionListener = context
        }
    }

    private fun setupViews() {
        binding.login.setOnClickListener {
            val username = binding.username.editText?.text.toString()
            val password = binding.password.editText?.text.toString()
            Valid.isValidUsername(username)?.let {
                SnackbarUtil.showWarningMessage(binding.root, "请正确填写用户名")
                return@setOnClickListener
            }
            Valid.isValidPassword(password)?.let {
                SnackbarUtil.showWarningMessage(binding.root, "请正确填写密码")
                return@setOnClickListener
            }
            val remember = binding.rememberMe.isChecked

            // 后门
            if (username == "lmio" && password == "123456") {
                // 登录成功，保存用户信息跳转到 MainActivity
                Account.init(this.requireContext())
                Account.save(Account(1, "user",
                    username, "123@123.com",
                    "", "", "", 1, 0, "", ""))
                interactionListener?.toMain()
            }

            val call = Requests.getApiService().login(username, password, remember)
            Requests.enqueue(call, binding.root, success = { message, account ->
                SnackbarUtil.showSuccessMessage(binding.root, message)
                // 登录成功，保存用户信息跳转到 MainActivity
                Account.init(this.requireContext())
                if (account != null) {
                    Account.save(account)
                }
                interactionListener?.toMain()
            })
        }

        binding.username.editText?.doOnTextChanged { text, _, _, _ ->
            val username = text.toString()
            val message = Valid.isValidUsername(username)
            if (message != null) {
                binding.username.error = message
            } else {
                binding.username.error = null
            }
        }

        binding.password.editText?.doOnTextChanged { text, _, _, _ ->
            val password = text.toString()
            val message = Valid.isValidPassword(password)
            if (message != null) {
                binding.password.error = message
            } else {
                binding.password.error = null
            }
        }

        binding.toForget.setOnClickListener {
            // 处理忘记密码按钮点击事件
            findNavController().navigate(R.id.action_loginFragment_to_forgetFragment)
        }

        binding.toRegister.setOnClickListener {
            // 处理注册按钮点击事件
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
