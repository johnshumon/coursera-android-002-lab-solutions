package me.shumon.selfiemania;

/**
 * Created by kutimuti on 11/29/14.
 */


public class ImageDescription {
    private String mThumbPath;
    private String mImagePath;
    private String mDateTime;
    private String mDescription;

    /**
     * default constructor
     */
    public ImageDescription()
    {}


    /**
     * Constructor
     * @param descripton
     * @param imagePath
     * @param dateTime
     */
    public ImageDescription(String descripton, String imagePath, String dateTime)
    {
        mDescription = descripton;
        mImagePath = imagePath;
        mDateTime = dateTime;
    }
    /**
     * Setter methods..
     * @param path
     */
    public void setThumbPath(String path)
    {
        mThumbPath = path;
    }

    public void setImagePath(String path)
    {
        mImagePath = path;
    }

    public void setDateTime(String date)
    {
        mDateTime = date;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }


    /**
     * Getter methods
     * @return
     */
    public String getThumbPath()
    {
        return mThumbPath;
    }

    public String getImagePath()
    {
        return mImagePath;
    }

    public String getDateTime()
    {
        return mDateTime;
    }

    public String getDescription()
    {
        return mDescription;
    }
}
