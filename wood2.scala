import math._
import scala.util._
import scala.io.StdIn._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable._

//a sample class that contains sampla variables 
class Sample(val id : Int,val carried_by:Int,val rank : Any, val gain : Any, val health : Int, val cost : ArrayBuffer[Int]){
    override def toString(): String = {
        return id.toString();
    }
}
//robot class that contains robot variables
class Robot(val storage: ArrayBuffer[Int] , val target: String ="")

object Player extends App {
	def goAndConnect( module: String, data: Any, position: String) = {
		val b = data.toString()

		//if robot is in the target position then connect that module
		//if robot is in the DIAGNOSIS or LABOROTORY then it will be "CONNECT sample's id" (integer value)
		//if robot is in the MOLECULES then it will be "CONNECT neededMolecule"(neededMolecule is one of the "ABCDE")
		if (position == module){ 
			println("CONNECT " + b)
		}
		
		//if robot is not in the target position then go to target(module)
		else {
			println("GOTO " + module)
		}
	}

    val projectCount = readLine.toInt
    for (i <- 0 until projectCount) {
        val Array(a, b, c, d, e) = (readLine split " ").map (_.toInt)
    }

    // game loop
    while(true) {  
		//sample's array
		val samples = ArrayBuffer[Sample]()
		
		//robot's array(my robot, dr. jekyl)
        val robots = ArrayBuffer[Robot]()

        for(i <- 0 until 2) {
            val Array(target, _eta, _score, _storageA, _storageB, _storageC, _storageD, _storageE, _expertiseA, _expertiseB, _expertiseC, _expertiseD, _expertiseE) = readLine split " "
            val eta = _eta.toInt
            val score = _score.toInt
            val storageA = _storageA.toInt
            val storageB = _storageB.toInt
            val storageC = _storageC.toInt
            val storageD = _storageD.toInt
            val storageE = _storageE.toInt
            val expertiseA = _expertiseA.toInt
            val expertiseB = _expertiseB.toInt
            val expertiseC = _expertiseC.toInt
            val expertiseD = _expertiseD.toInt
            val expertiseE = _expertiseE.toInt
			//store molecules in the storages array
            var storages = ArrayBuffer(storageA, storageB,storageC,storageD,storageE)
			//create my robot
            val robot = new Robot(storages,target)
			//add that robot to robots array
            robots += robot
        }
		
        val Array(availableA, availableB, availableC, availableD, availableE) = (readLine split " ").map (_.toInt)
        val sampleCount = readLine.toInt
        
		for(i <- 0 until sampleCount) {
            val Array(_sampleId, _carriedBy, _rank, expertiseGain, _health, _costA, _costB, _costC, _costD, _costE) = readLine split " "
            val sampleId = _sampleId.toInt
            val carriedBy = _carriedBy.toInt
            val rank = _rank.toInt
            val health = _health.toInt
            val costA = _costA.toInt
            val costB = _costB.toInt
            val costC = _costC.toInt
            val costD = _costD.toInt
            val costE = _costE.toInt
	
			//add costs to costs array 
			val costs = ArrayBuffer(costA,costB,costC,costD,costE)
			//create sample
			val sample = new Sample(sampleId,carriedBy,rank,expertiseGain,health,costs)
			samples += sample
        }
		
	//empty array for best sample(if I decleare as null, console show error)
	val c2 = ArrayBuffer(0,0,0,0,0)
	//best sample for better than dr. jekyl 
	var bestSample = new Sample(0,0,null,null,0,c2)
	var maxHealth : Int = 0
	//0th robot which is mine robot
	val me = robots(0)
	//molecule types
	val a = ArrayBuffer("A","B","C","D","E");
          
	//if sample's health in samples greater than max health
	//and if sample is not carried by dr. jekyl and in the cloud
	//then bestSample equal to that sample and maxHealth equal to that sample's health
	for(sample <- samples) {
		if(sample.health > maxHealth && sample.carried_by != 1) {
			bestSample = sample
			maxHealth  = sample.health
		}
	}
		
	//if bestSample not carried by my robot
	//then GOTO DIAGNOSIS
	//else bestSample carried by my robot
	//robot has to connect that bestSample's molecules that is some of the "ABCDE"
	if(bestSample.carried_by != 0) {
		goAndConnect("DIAGNOSIS",bestSample,me.target)
	} else {
		var neededMol: Any = null
		for(i <- 0 until  5) {
			if(me.storage(i) < bestSample.cost(i)) {
				neededMol = a(i)
			}   
		}
		if(neededMol != null) { 
			goAndConnect("MOLECULES",neededMol,me.target)
		} else {
			goAndConnect("LABORATORY",bestSample.id,me.target) 
		}  
	}
}
}


