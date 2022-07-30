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
    
    var secondLine = lines.get(1)
    var secondLineArr = secondLine.split(" ")
    var presName = secondLineArr[0]
    var slideNum = secondLineArr[1]
    
    if (("Merged.pptm" == presName) && (1 ==slideNum )) {
        println "finished check on home slide"
    } else {
         println "continuing to check time..."
         
         var now = LocalDateTime.now()
         var nowStr = now.format("HH:mm")
         var nowStrArr = nowStr.split(":")
         var hours = Integer.parseInt(nowStrArr[0])
         var mins = Integer.parseInt(nowStrArr[1])
         
         var firstLine = lines.get(0)
         var firstLineArr = firstLine.split(":")
         var oldHours = Integer.parseInt(firstLineArr[0])
         var oldMins = Integer.parseInt(firstLineArr[1])
         
         var totalMins = 60*hours + mins
         var totalOldMins = 60*oldHours + oldMins
         var diffMins = totalMins - totalOldMins
         
         println "diffMins = ${diffMins}"
         
         if (diffMins >= INACTIVITY_MINS) {
             println "killing POWERPNT.exe"
             //"taskkill /f /im POWERPNT.exe".execute()
             
              println "starting POWERPNT.exe"
             //'start "C:\Program Files\Microsoft Office\root\Office16\powerpnt" "Merged.ppsm"'.execute() 
         }
    }
}

while (true) {
    check()
    println "sleeping..."
    sleep 60_000
}
