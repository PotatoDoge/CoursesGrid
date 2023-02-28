package com.example.coursesgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursesgrid.model.Topic
import com.example.coursesgrid.ui.theme.CoursesGridTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background) {
                CourseApp()
            }

        }
    }
}

@Composable
fun CourseApp(){
    CoursesGridTheme {
        CourseGrip()
    }
}

/**
 * Composable that creates the grid/list based on the given topics
 */
@Composable
fun CourseGrip(){
    CourseList(courses = DataSource.topics)
}

/**
 * Composable that arranges the grid in order to have 2 equally distributed columns
 */
@Composable
fun CourseList(courses: List<Topic>, modifier: Modifier = Modifier ){
    LazyVerticalGrid(columns = GridCells.Fixed(2), verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)){
        items(courses){
            course -> CourseCard(course = course)
        }
    }
}

/**
 * Composable of the card that is generated for each of the course
 */
@Composable
private fun CourseCard(course: Topic, modifier:Modifier = Modifier){
    Card (elevation = 4.dp) {
        Row() {
            Box() {
               Image(
                   painter = painterResource(id = course.imageResourceId),
                   contentDescription = stringResource(id = course.stringResourceId),
                   modifier = Modifier
                       .width(68.dp)
                       .height(68.dp)
                       .aspectRatio(1f), contentScale = ContentScale.Crop)
            }
            Column {
                Text(text = stringResource(id = course.stringResourceId), style = MaterialTheme.typography.body2, modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp)) // ends text
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.ic_grain), contentDescription = null, modifier = Modifier.padding(start = 8.dp).size(12.dp))
                    Text(text = course.students.toString(), style = MaterialTheme.typography.caption, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun CardPreview(){
    CourseCard(course = DataSource.topics[12])
}