package objects.models;

public class ConsumableSearchForm implements SearchForm{

    public String consumableName;
    public String consumableType;
    public boolean isAlcoholic;
    public boolean hasHighCaffeine;
    public boolean hasMeat;
    public boolean hasDairy;
    public String timeOfMeal = "";
    public boolean favorite;
    public String mainTasteElement;

    @Override
    public String buildConditionsString(){
        StringBuilder sb = new StringBuilder();

        if (!consumableName.isEmpty()) {
            sb.append("consumable_name LIKE \"").append(consumableName).append("\"");
        }
        else{
            sb.append("consumable_name LIKE \"%\"");
        }

        if (!consumableType.isEmpty()){
            sb.append(" AND consumable_type=\"").append(consumableType).append("\"");
            if (!timeOfMeal.isEmpty()){
                sb.append(" AND meal_time=\"").append(timeOfMeal).append("\"");
            }
            if (isAlcoholic){
                sb.append(" AND is_alcoholic=\"").append(true).append("\"");
            }
            if (hasHighCaffeine){
                sb.append(" AND high_caffeine=\"").append(true).append("\"");
            }
        }

        if (hasMeat){
            sb.append(" AND has_meat=\"").append(true).append("\"");
        }
        if (hasDairy){
            sb.append(" AND has_dairy=\"").append(true).append("\"");
        }
        if (favorite) {
            sb.append(" AND is_favorite=\"").append(true).append("\"");
        }
        if (!mainTasteElement.isEmpty()){
            sb.append(" AND taste_elements=\"").append(mainTasteElement).append("\"");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    @Override
    public String getSearchFormType() {
        return "consumable";
    }
}
