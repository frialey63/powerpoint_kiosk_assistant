import static Constants.*
import java.time.LocalDateTime

class Constants {
    static final FILE_PATH = "c:/users/paul/slide_changed.txt"
    static final INACTIVITY_MINS = 10
}

def check() {
    println "starting check..."
    
    var myFile = new File(FILE_PATH)
    var lines = myFile.readLines()
    var lineStr = lines.get(0)
    var lineArr = lineStr.split(" ")
    
    var time = lineArr[0]
    var presName = lineArr[1]
    var slideNum = lineArr[2]
    
    println "time = ${time}, presName = ${presName}, slideNum = ${slideNum}"
    
    if (("Merged.pptm" == presName) && (1 == slideNum )) {
        println "finished check on home slide"
    } else {
         println "continuing to check time..."
         
         var now = LocalDateTime.now()
         var nowStr = now.format("HH:mm")
         var nowStrArr = nowStr.split(":")
         var hours = Integer.parseInt(nowStrArr[0])
         var mins = Integer.parseInt(nowStrArr[1])
         
         var timeArr = time.split(":")
         var oldHours = Integer.parseInt(timeArr[0])
         var oldMins = Integer.parseInt(timeArr[1])
         
         println "time: $time -> $oldHours $oldMins"
         println "nowStr: $nowStr -> $hours $mins"
         
         var totalMins = 60*hours + mins
         var totalOldMins = 60*oldHours + oldMins
         var diffMins = totalMins - totalOldMins
         
         println "diffMins = ${diffMins}"
         
         if (diffMins >= INACTIVITY_MINS) {
             println "killing POWERPNT.exe"
             //"taskkill /f /im POWERPNT.exe".execute()
             
             println "starting POWERPNT.exe"
             //"C:/Users/Paul/Stage/interactive-museum-display/run.bat".execute() 
         }
    }
}

while (true) {
    check()
    println "sleeping..."
    sleep 60_000
}
