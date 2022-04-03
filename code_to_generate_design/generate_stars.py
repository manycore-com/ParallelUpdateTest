#!/usr/bin/python3

images = ["ds", "rosa", "badboll", "disco"]

NBR_ROWS = 20
NBR_COLS = 16

for y in range(NBR_ROWS):
    for x in range(NBR_COLS):


        print("            <ImageView")
        print('                android:id="@+id/image_' + str(x) + '_' + str(y) + '"')
        print('                android:layout_width="25dp"')
        print('                android:layout_height="25dp"')
        print('                app:srcCompat="@drawable/' + images[(x + y + x * y) & 3] + '" />')
        print("")

print("")
print("")
print("")

for y in range(NBR_ROWS):
    for x in range(NBR_COLS):
        print('        imageViewArray[' + str(x) + '][' + str(y) + '] = (ImageView) findViewById(R.id.image_' + str(x) + '_' + str(y) + ');')
