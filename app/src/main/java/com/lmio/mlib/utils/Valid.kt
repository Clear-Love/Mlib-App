package com.lmio.mlib.utils

class Valid {

    companion object {
        private val EMAIL_PATTERN = Regex(pattern = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
        fun isValidUsername(username: String): String? {
            if (username.length !in 3..10) {
                return "用户名长度应该为3-10个字符"
            }
            val regex = Regex("^[a-zA-Z0-9]+$")
            if (!regex.matches(username)) {
                return "用户名只能包含数字或英文字母"
            }
            return null
        }

        fun isValidEmail(email: String): Boolean {
            return EMAIL_PATTERN.matches(email)
        }

        fun isValidPassword(password: String): String? {
            if (password.length < 6) {
                return "密码长度应该大于6个字符"
            }
            if (password.length > 16) {
                return "密码长度应该小于16个字符"
            }
            return null
        }
    }

}