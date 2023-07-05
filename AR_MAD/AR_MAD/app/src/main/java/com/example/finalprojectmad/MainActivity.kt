package com.example.finalprojectmad

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode

class MainActivity : AppCompatActivity() {


    lateinit var sceneView: ArSceneView
    lateinit var placeButton: ExtendedFloatingActionButton

    /**   modelNode is an instance of the ArModelNode class,
     *  which represents a 3D model that can be placed in the AR scene. **/

    private lateinit var modelNode: ArModelNode


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SceneView from layout
        sceneView = findViewById(R.id.sceneView);

        // Initialize placeButton from layout
        placeButton = findViewById(R.id.place);

        // Set click listener for placeButton
        placeButton.setOnClickListener {
            placeModel()
        }

        // Create a new instance of ArModelNode and load the GLB model asynchronously
        modelNode = ArModelNode().apply {
            loadModelGlbAsync(
                glbFileLocation = "models/office_chair.glb"
            )
            {
                sceneView.planeRenderer.isVisible = true
            }


            /**The onAnchorChanged property of the modelNode is set to a lambda function,
             * which sets the isGone property of placeButton when the anchor (position and orientation) of the model changes.**/


            onAnchorChanged = {
                placeButton.isGone
            }


        }

        // Add the modelNode to the sceneView
        sceneView.addChild(modelNode)

    }

    private fun placeModel(){
        modelNode?.anchor()
        sceneView.planeRenderer.isVisible = false
    }


}