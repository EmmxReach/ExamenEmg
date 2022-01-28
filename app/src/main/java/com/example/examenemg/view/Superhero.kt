package com.example.examenemg.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.examenemg.databinding.ActivitySuperheroBinding
import com.example.examenemg.helpers.HtmlHelper
import com.example.examenemg.model.SuperheroModel
import com.example.examenemg.viewmodel.InfoSuperheroViewModel
import com.squareup.picasso.Picasso

class Superhero : AppCompatActivity() {

    private lateinit var binding: ActivitySuperheroBinding
    private val infoSuperheroModel: InfoSuperheroViewModel by viewModels()
    private lateinit var superhero: SuperheroModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        superhero = getExtrasSuperhero()
        binding = ActivitySuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        infoSuperheroModel.onCreate(superhero)
        infoSuperheroModel.superhero.observe(this, { data -> loadUI(data) })
    }

    private fun loadUI(superheroModel: SuperheroModel){

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)

        Picasso.get().load(superheroModel.image.url).into(binding.ivPhotoSuperhero)
        binding.identifier.text = HtmlHelper.castSource("<b>Identifier:</b> ${superheroModel.id}")
        binding.tvfullNameInfo.text = HtmlHelper.castSource("<b>Full Name:</b> ${superheroModel.biography.full_name}")
        binding.tvnameInfo.text = HtmlHelper.castSource("<b>Name:</b> ${superheroModel.name}")

        binding.tvfn.text = superheroModel.biography.full_name
        binding.tvEgos.text = superheroModel.biography.alter_egos
        binding.tvAliases.text = superheroModel.biography.aliases.joinToString(", ")
        binding.tvBirth.text = superheroModel.biography.place_of_birth
        binding.tvFirstApp.text = superheroModel.biography.first_appearance
        binding.tvPublisher.text = superheroModel.biography.publisher
        binding.tvGender.text = superheroModel.appearance.gender
        binding.tvRace.text = superheroModel.appearance.race
        binding.tvHeight.text = superheroModel.appearance.height.joinToString (", ")
        binding.tvWeight.text = superheroModel.appearance.weight.joinToString (", ")
        binding.tvEyeColor.text = superheroModel.appearance.eye_color
        binding.tvHairColor.text = superheroModel.appearance.hair_color
        binding.tvOcupation.text = superheroModel.work.occupation
        binding.tvBase.text = superheroModel.work.base
        binding.tvGroupAffiliation.text = superheroModel.connections.group_affiliation
        binding.tvRelatives.text = superheroModel.connections.relatives
    }

    private fun getExtrasSuperhero(): SuperheroModel = intent.getSerializableExtra("data") as SuperheroModel

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}