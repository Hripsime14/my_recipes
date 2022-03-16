package com.example.myrecipes.ui.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myrecipes.R

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    protected abstract val viewModel: BaseViewModel

}