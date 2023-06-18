package com.lmio.mlib.ui.auth

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lmio.mlib.R
import com.lmio.mlib.databinding.FragmentRegisterBinding
import com.lmio.mlib.utils.Requests
import com.lmio.mlib.utils.SnackbarUtil
import com.lmio.mlib.utils.Valid

import java.util.Timer
import java.util.TimerTask

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var interactionListener: FragmentInteractionListener? = null
    private var coldTime: Int = 0
    private lateinit var timer: Timer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        interactionListener?.updateTitle("注册")
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            @SuppressLint("SetTextI18n")
            override fun run() {
                if (coldTime > 0) {
                    coldTime--
                    activity?.runOnUiThread {
                        // 在 UI 线程上执行按钮的可用性更新
                        binding.getCode.text = coldTime.toString() + "秒"
                        binding.getCode.isEnabled = coldTime == 0
                        if (coldTime == 0) {
                            binding.getCode.text = context?.getString(R.string.get_validCode)
                        }
                    }
                }
            }
        }, 0, 1000)
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
        binding.toLogin.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
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

        binding.rePassword.editText?.doOnTextChanged{text, _, _, _ ->
            if (text.toString() != binding.password.editText?.text.toString()) {
                binding.rePassword.error = "两次输入密码不一致"
            } else {
                binding.rePassword.error = null
            }
        }

        binding.email.editText?.doOnTextChanged {text, _, _, _ ->
            if (!Valid.isValidEmail(text.toString())) {
                binding.email.error = "请输入正确的邮件地址"
            } else {
                binding.email.error = null
            }
        }

        binding.validCode.editText?.doOnTextChanged { text, _, _, _ ->
            if (text?.length != 6) {
                binding.validCode.error = "6位验证码"
            }else {
                binding.validCode.error = null
            }
        }

        binding.getCode.setOnClickListener {
            val email = binding.email.editText?.text.toString()
            if (!Valid.isValidEmail(email)) {
                SnackbarUtil.showWarningMessage(binding.root, "请输入正确的邮件地址")
                return@setOnClickListener
            }
            val call = Requests.getApiService().validateRegisterEmail(email)
            coldTime = 60
            Requests.enqueue(call, binding.root,
                failure = { message, _ ->
                    coldTime = 0
                    SnackbarUtil.showSuccessMessage(binding.root, message)
                },
                error = {
                    coldTime = 0
                    SnackbarUtil.showSuccessMessage(binding.root, "请求超时")
                }
            )
        }

        binding.register.setOnClickListener {
            val username = binding.username.editText?.text.toString()
            val password = binding.password.editText?.text.toString()
            val re_password = binding.rePassword.editText?.text.toString()
            val validCode = binding.validCode.editText?.text.toString()
            val email = binding.email.editText?.text.toString()
            Valid.isValidUsername(username)?.let {
                SnackbarUtil.showWarningMessage(binding.root, it)
                return@setOnClickListener
            }
            Valid.isValidPassword(password)?.let {
                SnackbarUtil.showWarningMessage(binding.root, it)
                return@setOnClickListener
            }
            if (password != re_password) {
                SnackbarUtil.showWarningMessage(binding.root, "两次输入密码不一致")
                return@setOnClickListener
            }
            if (!Valid.isValidEmail(email)) {
                SnackbarUtil.showWarningMessage(binding.root, "请输入正确的邮件地址")
                return@setOnClickListener
            }
            if (validCode.length != 6) {
                SnackbarUtil.showWarningMessage(binding.root, "请正确填写验证码")
                return@setOnClickListener
            }
            val call = Requests.getApiService().registerUser(username, password, email, validCode)
            Requests.enqueue(call, binding.root, success = { message, _ ->
                SnackbarUtil.showSuccessMessage(binding.root, message)
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
        _binding = null
    }
}