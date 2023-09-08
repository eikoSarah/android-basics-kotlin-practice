/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    private val dogs = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val dogImage : ImageView? = view?.findViewById(R.id.dog_imageView)
        val dogName : TextView? = view?.findViewById(R.id.dog_name)
        val dogAge : TextView? = view?.findViewById(R.id.dog_age)
        val dogHabit : TextView? = view?.findViewById(R.id.dog_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        //  使用條件確定佈局類型並進行相應設置。如果佈局變量是 Layout.GRID，則應使用網格列表項。否則應使用垂直水平列表項。
        return when(viewType){
            Layout.GRID -> {
                val adapterLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.grid_list_item, parent, false)
                DogCardViewHolder(adapterLayout)
            }

            else -> {
                val adapterLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.vertical_horizontal_list_item, parent, false)
                DogCardViewHolder(adapterLayout)
            }
        }
    }

    override fun getItemCount(): Int = dogs.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        //獲取當前位置的數據
        val dog = dogs[position]
        //設置當前狗的圖片資源
        val dogImageRes = dog.imageResourceId
        //設置當前狗的名字文本
        val dogNameText =  dog.name
        //設置當前狗的年齡文本
        val dogAgeNum = dog.age

        val resources = context?.resources

        //  通過將愛好傳遞給 R.string.dog_hobbies 字符串常量來設置當前狗的愛好的文本。
        val dogHabitText = dog.hobbies
        //  將參數傳遞給字符串資源如下所示：resources?.getString(R.string.dog_hobbies,dog.hobbies)

        holder.dogImage?.setImageResource(dogImageRes)
        holder.dogAge?.text = resources?.getString(R.string.dog_age, dogAgeNum)
        holder.dogName?.text = dogNameText
        holder.dogHabit?.text = resources?.getString(R.string.dog_hobbies, dogHabitText)

    }
}
