package com.lmio.mlib.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lmio.mlib.R
import com.lmio.mlib.databinding.FragmentHomeBinding
import com.lmio.mlib.entity.Book
import com.lmio.mlib.stores.AppDatabase
import com.lmio.mlib.ui.FragmentCallback
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookList: RecyclerView
    private lateinit var bookListAdapter: BookListAdapter
    private lateinit var callback: FragmentCallback
    private lateinit var db: AppDatabase

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val root = binding.root

        bookList = root.findViewById(R.id.bookList)
        bookListAdapter = BookListAdapter()
        bookList.adapter = bookListAdapter

        db = callback.getDb()
        GlobalScope.launch(Dispatchers.IO) {
            val books = db.bookDao().getAll()
            activity?.runOnUiThread {
                // 将图书信息添加到适配器中
                bookListAdapter.submitList(books)
            }
        }
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as FragmentCallback
    }

    private class BookListAdapter : ListAdapter<Book, BookViewHolder>(BookDiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_book, parent, false)
            return BookViewHolder(view)
        }

        override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
            val book = getItem(position)
            holder.bind(book)
        }
    }

    private class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val bookCover: ImageView = itemView.findViewById(R.id.book_cover)
        private val bookTitle: TextView = itemView.findViewById(R.id.book_title)

        fun bind(book: Book) {
            // 加载图书封面
            Glide.with(itemView.context)
                .load(book.coverImage)
                .into(bookCover)

            // 设置图书标题
            bookTitle.text = book.title
        }
    }

    private class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.bookId == newItem.bookId
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}