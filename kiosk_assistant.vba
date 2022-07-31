
Public Const filePath As String = "c:\users\paul\slide_changed.txt"

Sub OnSlideShowPageChange(ByVal SSW As SlideShowWindow)
    WriteSlideChangeFile SSW
End Sub

Sub WriteSlideChangeFile(ByVal SSW As SlideShowWindow)
    Open filePath For Output As 1
        Print #1, Format(Now(), "HH:mm:ss") + " " + Application.ActivePresentation.Name + Str(SSW.View.CurrentShowPosition) + " EOL"
    Close #1
End Sub

